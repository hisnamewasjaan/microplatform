import {Component} from '@angular/core';
import {AppService, IUser, User} from './app.service'

@Component({
    selector: 'user-details',
    providers: [AppService],
    template: `
        <div class="container">
            <h1 class="col-sm-12">User Details</h1>
            <div class="col-sm-12">
                <label class="col-sm-3">ID</label> <span>{{user.id}}</span>
            </div>
            <div class="col-sm-12">
                <label class="col-sm-3">User name: </label> <span>{{user.preferred_username}}</span>
            </div>
            <div class="col-sm-12">
                <button class="btn btn-primary" (click)="getUser()" type="submit">get User</button>
            </div>
        </div>`
})

export class UserComponent {
    public user = new User(0, 'n/a');

    constructor(private _service: AppService) {
    }

    getUser() {
        this._service.getUser()
            .subscribe(
                (data: IUser) => this.user = new User(data.id, data.preferred_username) as IUser);
            //     error => this.ad.name = 'Error');
    }

}
