import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from "./home/home.component";
import { UserComponent } from "./user/user.component";
import { RouterModule } from "@angular/router";
import { AdDetailsComponent } from './ad-details/ad-details.component';
import {ReactiveFormsModule} from "@angular/forms";
import { AdsComponent } from './ads/ads.component';
import { AdsHomeComponent } from './ads-home/ads-home.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UserComponent,
    AdDetailsComponent,
    AdsComponent,
    AdsHomeComponent
  ],
  imports: [
    BrowserModule,
    // FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: '', component: HomeComponent, pathMatch: 'full'},
      {path: 'ads/:adId', component: AdDetailsComponent}
        ],
        {onSameUrlNavigation: 'reload'}
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
