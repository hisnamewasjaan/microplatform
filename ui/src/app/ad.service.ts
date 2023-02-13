import {Injectable} from '@angular/core';
import {catchError, Observable, of} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Cookie} from "ng2-cookies";
import {Ad, ADS, IAd} from "./ad";


@Injectable({
  providedIn: 'root'
})
export class AdService {

  private adsUrl = 'http://localhost:8081/api/ads/';

  constructor(private _http: HttpClient) {
  }

  getMyAds(): Observable<Ad[]> {
    // return of(ADS);
    return this.getAllMyAds();
  }

  getMyAd(id: string): Observable<Ad|undefined> {
    // return of(ADS.find(ad => ad.id === id));
    return this.getAdById(id);
  }

  /**
   * GET ad by id.
   * Will 404 if id not found
   */
  getAdById(id: string): Observable<Ad | undefined> {
    return this._http
        .get<Ad>(
            this.adsUrl + id,
            this.httpOptions)
        .pipe(
            catchError(this.handleError<Ad>('getAdById id=${id}'))
        );
  }

  getAllAds(): Observable<Ad[]> {
    return this._http
        .get<IAd[]>(this.adsUrl, this.httpOptions)
        .pipe(
            catchError(this.handleError<Ad[]>('getAllMyAds', []))
        );
  }

  getAllMyAds(): Observable<Ad[]> {
    return this._http
        .get<Ad[]>(this.adsUrl + 'myAds', this.httpOptions)
        .pipe(
            catchError(this.handleError<Ad[]>('getAllMyAds', []))
        );
  }

  createAd(ad: { } ): Observable<Ad> {
    console.log('Create', ad);

    return this._http
        .post(this.adsUrl, ad, this.httpOptions)
        .pipe(
            catchError(this.handleError<Ad>('createAd'))
        )
        ;
  }

  updateAd(ad: Ad): Observable<any> {
    return this._http
        .put(this.adsUrl, ad, this.httpOptions)
        .pipe(catchError(this.handleError<any>('updateAd')));
  }

  /**
   * DELETE: delete the ad from the server
   */
  deleteAd(id: number): Observable<Ad> {
    const url = `${this.adsUrl}/${id}`;

    return this._http
        .delete<Ad>(url, this.httpOptions)
        .pipe(
            catchError(this.handleError<Ad>('deleteAd'))
        );
  }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + Cookie.get('access_token')
    })
  };

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
