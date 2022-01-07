import {Component, Input, OnInit} from '@angular/core';
import {Login} from "./login";
import {LoginService} from "./login.service";
import {Router} from "@angular/router";


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
  @Input() loginStatus: string = "false";
  @Input() id : number = 0;

  constructor(private loginService: LoginService, private router : Router) {}

  ngOnInit(): void {
    this.loginStatus = "false";
  }

  /**
   *
   * @param login
   */
  onToggleLoginsCheck(login : Login) {
    this.loginService.getLogin(login).subscribe( {
      next: (data) => {
        console.log("ici");
        if (data.email == login.email && data.password == login.password) {
          this.loginStatus = "true";
          this.router.navigate(['person/'+ data.idLogin]);
        }
    },
      error :  () => {
      this.loginStatus = "false";
        this.router.navigate(['login']);
    },
      complete: () => console.info('complete')
    });

  }

  getStatus() {
    return this.loginStatus;
  }

}
