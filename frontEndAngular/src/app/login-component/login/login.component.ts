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

  @Input() loginsArrayFromApp = [
    {
      email: "",
      password:"",
      status: ""
    }
  ] ;

  @Input() isAuthLogin : boolean = false;

  constructor(private loginService: LoginService) {}

  // public getLogin():void {
  //   this.loginService.getLogin().subscribe(
  //     (response:Login[]) => {
  //       this.loginResponse = response;
  //     },
  //     (error:HttpErrorResponse) => {
  //       alert(error.message);
  //     }
  //   );
  // }

  ngOnInit(): void {
    this.loginService.getLogin().subscribe(data => {
      this.loginResponse = data;
    });
  }

  /**
   * methods test
   */
  onToggleLoginsStatus() {
    if (this.loginsArrayFromApp[0].status === "false")
      this.loginsArrayFromApp[0].status = "true";
    else
      this.loginsArrayFromApp[0].status = "false";
    // console.log(this.loginsArrayFromApp[0].status)
  }

  onToggleLoginsCheck() {
    // @ts-ignore
    console.log(this.loginResponse[0].email)
    // @ts-ignore
    console.log(this.loginResponse[0].password)
  }

  getStatus() {
    return this.loginsArrayFromApp[0].status;
  }

}
