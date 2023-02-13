import {Component, OnInit} from '@angular/core';

import {ActivatedRoute} from '@angular/router';

import {Ad} from '../ad'
import {AdService} from "../ad.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-ad-details',
  templateUrl: './ad-details.component.html',
  styleUrls: ['./ad-details.component.css']
})
export class AdDetailsComponent implements OnInit {

  ad: Ad | undefined

  constructor(private route: ActivatedRoute,
              private location: Location,
              private adService: AdService) {
  }

  ngOnInit(): void {
    // First get the ad id from the current route.
    const routeParams = this.route.snapshot.paramMap;
    const adIdFromRoute = String(routeParams.get('adId'));
    console.log(routeParams)
    // Find the ad that correspond with the id provided in route.
    this.adService
        .getMyAd(adIdFromRoute)
        .subscribe(ad =>
            this.ad = ad);
  }

  goBack(): void {
    this.location.back()
  }

}
