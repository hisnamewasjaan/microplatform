import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

type Greeting = {
  id?: String;
  content?: String;
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'ui';
  greeting: Greeting = {};
  // greeting = {};
  // greeting: Object = {'xid': 'XXX', 'xcontent': 'Hello World', content: 'sdf', id: 'XXX'};

  // constructor(private http: HttpClient) {
  //   http
  //       .get('http://localhost:8080/resource')
  //       .subscribe(data =>
  //           this.greeting = data
  //       );
  // }

}
