import {Component, OnInit} from '@angular/core';
import {AppService} from "../app.service";
import {AdService} from "../ad.service";
import {FormBuilder} from "@angular/forms";
import {Ad, IAd} from "../ad";

@Component({
  selector: 'app-ads-home',
  providers: [AppService],
  templateUrl: './ads-home.component.html',
  styleUrls: ['./ads-home.component.css']
})
export class AdsHomeComponent implements OnInit {

  public ad? = new Ad();
  public ad2 = new Ad();
  ads: Ad[] = [];

  adForm = this.formBuilder.group({
    name: '',
    description: '',
    price: ''
  });

  constructor(private _service: AppService,
              private _adService: AdService,
              private formBuilder: FormBuilder,
  ) {
  }

  ngOnInit(): void {
    this._adService.getMyAds().subscribe(ads => this.ads = ads);
  }

  onSubmit(): void {
    // Process checkout data here
    // this.items = this.cartService.clearCart();
    this.createAdFromFormData();
    console.warn('Your ad has been submitted', this.adForm.value);
    this.adForm.reset();
  }

  getAd(id: number) {
    this._adService.getAdById(id)
        .subscribe(ad =>
            this.ad = ad);
  }

  createAdFromFormData() {
    this._adService.createAd(this.adForm.value)
        .subscribe((data: Ad) => this.ads.push(data));
  }

}
