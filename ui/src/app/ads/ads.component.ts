import {Component, Input, OnInit} from '@angular/core';
import {AdService} from "../ad.service";
import {Ad} from "../ad";

@Component({
  selector: 'app-ads',
  templateUrl: './ads.component.html',
  styleUrls: ['./ads.component.css']
})
export class AdsComponent implements OnInit {

  @Input() ads?: Ad[];

  constructor(private adService: AdService) {
  }

  ngOnInit(): void {
  }

}
