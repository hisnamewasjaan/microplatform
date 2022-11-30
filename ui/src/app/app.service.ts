import {Injectable} from "@angular/core";
import {Cookie} from 'ng2-cookies';
import {HttpClient, HttpHeaders} from "@angular/common/http";
// import {Observable} from "rxjs";
import { Observable } from 'rxjs';
import { throwError, of } from 'rxjs';
import {map} from "rxjs";
import {catchError} from "rxjs";
import * as rxjs from 'rxjs';


export class Ad {
    constructor(
        public id: number,
        public name: string) {
    }
}

export interface IAd {
    id: number;
    name: string;
}

@Injectable()
export class AppService {
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

    saveToken(token: any) {
        var expireDate = new Date().getTime() + (1000 * token.expires_in);
        Cookie.set("access_token", token.access_token, expireDate);
        console.log('Obtained Access token');
        window.location.href = 'http://localhost:4200';
    }

    getResource(resourceUrl: string): Observable<any> {
        var headers = new HttpHeaders({
            'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
            'Authorization': 'Bearer ' + Cookie.get('access_token')
        });
        // return this._http.get(resourceUrl, {headers: headers})
        //     .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
        return this._http.get<IAd>(resourceUrl, {headers: headers})
            // .pipe(
            //     catchError(this.handleError<IAd>('getResource'))
            // )
            ;
    }

    getResources(resourceUrl: string): Observable<any> {
        var headers = new HttpHeaders({
            'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
            'Authorization': 'Bearer ' + Cookie.get('access_token')
        });
        return this._http.get<IAd[]>(resourceUrl, {headers: headers});
    }

    createResource(resourceUrl: string, ad: Ad): Observable<any> {
        var headers = new HttpHeaders({
            'Content-type': 'application/json',
            'Authorization': 'Bearer ' + Cookie.get('access_token')
        });
        console.log(ad); // log to console instead

        return this._http.post(resourceUrl, ad, {headers: headers})
            // .pipe(
            //     catchError(this.handleError<IAd>('createResource', ad))
            // )
        ;
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
