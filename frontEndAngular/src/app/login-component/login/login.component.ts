import {Component, Input, OnInit} from '@angular/core';
import {Login} from "./login";
import {LoginService} from "./login.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public loginResponse: Login[] = [];
  public loginForm: Login = new class implements Login {
    email: string = "";
    idLogin: number = 0;
    password: string = "";
  };

  @Input() loginName : string = "loginNameFromLoginComponent";
  loginTitle : string = "Login";
  @Input() loginStatus: string = "false";

  @Input() isAuthLogin : boolean = false;

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {

  }

  /**
   * methods test
   */
  onToggleLoginsStatus() {
    if (this.loginStatus === "false")
      this.loginStatus = "true";
    else
      this.loginStatus = "false";
  }

  /**
   * method to connect
   * rei
   * @param login
   */
  onToggleLoginsCheck(login : Login) {
    this.loginService.getLogin(login).subscribe(
      (data) => {

        // @ts-ignore
        if (data[0].email == login.email && data[0].password == login.password)
          this.loginStatus = "true";
    },
      () => {
      this.loginStatus = "false";
    }
    );

  }

  getStatus() {
    return this.loginStatus;
  }

}
