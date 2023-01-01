import {Injectable} from "@angular/core";
import {Cookie} from 'ng2-cookies';
import {HttpClient, HttpHeaders} from "@angular/common/http";
// import {Observable} from "rxjs";
import { Observable } from 'rxjs';
import { throwError, of } from 'rxjs';
import {map} from "rxjs";
import {catchError} from "rxjs";
import * as rxjs from 'rxjs';


export interface IUser {
    sub: string;
    preferred_username: string;
}
export class User {
    constructor(
        public sub: string,
        public preferred_username: string) {
    }
}

@Injectable()
export class AppService {
    /**
     * OAuth2 client id
     */
    public clientId = 'newClient';
    public redirectUri = 'http://localhost:4200/';


    constructor(private _http: HttpClient) {
    }

    retrieveToken(code: string) {

        let params = new URLSearchParams();
        params.append('grant_type', 'authorization_code');
        params.append('client_id', this.clientId);
        params.append('redirect_uri', this.redirectUri);
        params.append('code', code);

        let headers =
            new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'});

        this._http.post('http://localhost:8083/auth/realms/baeldung/protocol/openid-connect/token',
            params.toString(), {headers: headers})
            .subscribe(
                data => this.saveToken(data),
                err => alert('Invalid Credentials'));
    }

    getUser(): Observable<any>{
        let headers = new HttpHeaders({
            'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
            'Authorization': 'Bearer ' + Cookie.get('access_token')
        });

        return this._http.get<IUser>(
            'http://localhost:8083/auth/realms/baeldung/protocol/openid-connect/userinfo',
            {headers: headers})
            ;
    }

    saveToken(token: any) {
        let expireDate = new Date().getTime() + (1000 * token.expires_in);
        /* set cookie for storage only, it will never be sent */
        Cookie.set("access_token", token.access_token, expireDate);
        console.log('Obtained Access token');
        window.location.href = 'http://localhost:4200';
    }

    checkCredentials() {
        return Cookie.check('access_token');
    }

    logout() {
        Cookie.delete('access_token');
        window.location.reload();
    }

    /**
     * Handle Http operation that failed.
     * Let the app continue.
     *
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {

            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead

            // TODO: better job of transforming error for user consumption
            // this.log(`${operation} failed: ${error.message}`);

            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }

}
