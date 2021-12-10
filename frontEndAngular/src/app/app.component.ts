import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  isAuth = false;
  loginName : string = "loginNameFromApp";
  loginStatus: string = "true";

  logins = [
    {
      email: 'bobodivann@mail.com',
      password: 'letmeinbodi',
      status: 'false'
    }
  ]

  constructor() {
    setTimeout(
      () => {
        this.isAuth = true;
      },1000
    );
  }

  onToggleLoginsStatus() {
    if (this.logins[0].status === "false")
      this.logins[0].status = "true";
    else
      this.logins[0].status = "false";
    console.log(this.logins[0].status)
  }

  toggleStatus() {

  }

  getStatus() {
    return this.logins[0].status;
  }
}
