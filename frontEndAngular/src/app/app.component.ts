import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  //title = 'Pay My Buddy';
  isAuth = false;
  loginName : string = "loginNameFromApp";

  constructor() {
    setTimeout(
      () => {
        this.isAuth = true;
      },4000
    );
  }

  onTestConsole() {
    console.log('test');
  }
}
