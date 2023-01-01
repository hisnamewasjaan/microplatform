import { Component, OnInit } from '@angular/core';
import {AppService, IUser, User} from "../app.service";

@Component({
  selector: 'app-user',
  providers: [AppService],
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  public user = new User('n/a', 'n/a');

  constructor(private _service: AppService) {
  }

  ngOnInit(): void {
  }

  getUser() {
    this._service.getUser()
        .subscribe(
            (data: IUser) => this.user = new User(data.sub, data.preferred_username) as IUser);
    //     error => this.ad.name = 'Error');
  }

}
