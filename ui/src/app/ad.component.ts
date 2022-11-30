import {Component} from '@angular/core';
import {AppService, Ad, IAd} from './app.service'

@Component({
    selector: 'ad-details',
    providers: [AppService],
    template: `
        <div class="container">
            <h1 class="col-sm-12">Ad Details</h1>
            <div class="col-sm-12">
                <label class="col-sm-3">ID</label> <span>{{ad.id}}</span>
            </div>
            <div class="col-sm-12">
                <label class="col-sm-3">Name</label> <span>{{ad.name}}</span>
            </div>
            <div class="col-sm-12">
                <button class="btn btn-primary" (click)="getAd()" type="submit">get Ad</button>
            </div>
            <div class="col-sm-12">
                <button class="btn btn-primary" (click)="createAd()" type="submit">New Ad</button>
            </div>
            <div class="col-sm-12">
                <button class="btn btn-primary" (click)="getAllads()" type="submit">List Ads</button>
            </div>
        </div>`
})

export class AdComponent {
    public ad = new Ad(1, 'sample ad');
    public ad2 = new Ad(2, 'Another Ad');
    private foosUrl = 'http://localhost:8081/api/ads/';

    constructor(private _service: AppService) {
    }

    getAd() {
        this._service.getResource(this.foosUrl + this.ad.id)
            .subscribe((data: IAd) => this.ad = {id: data.id, name: data.name});
            // .subscribe(
            //     data => this.ad = data,
            //     error => this.ad.name = 'Error');
    }

    getAllads() {
        this._service.getResources(this.foosUrl)
            .subscribe((data: IAd) => this.ad = {id: data.id, name: data.name});
    }

    createAd() {
        this._service.createResource(this.foosUrl, this.ad2)
            .subscribe((data: IAd) => this.ad2 = {id: data.id, name: data.name});
    }
}
