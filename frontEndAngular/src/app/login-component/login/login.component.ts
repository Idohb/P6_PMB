import {Component, Input, OnInit} from '@angular/core';
import {Login} from "./login";
import {LoginService} from "./login.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "./auth.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public loginResponse: Login[] = [];
  public loginForm: Login = new class implements Login {
    email: string = "";
    id: number = 0;
    password: string = "";
    user_id:number = 0;
  };

  @Input() loginName : string = "loginNameFromLoginComponent";
  @Input() loginStatus: string = "false";
  @Input() id : number = 0;


  username: string ="";
  password : string="";
  errorMessage = 'Invalid Credentials';
  successMessage: string="";
  invalidLogin = false;
  loginSuccess = false;



  constructor(private loginService: LoginService,
              private router : Router,
              private authenticationService: AuthenticationService) {}

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
        if (data.email == login.email && data.password == login.password) {
          this.loginStatus = "true";
          this.loginService.setUserId(data.id);
          this.router.navigate(['person/'+ data.id]);
        }
    },
      error :  () => {
      this.loginStatus = "false";
        this.router.navigate(['login']);
    },
      complete: () => console.info('complete')
    });

  }

  onToggleLoginsCheckWithSecurity() {
     // this.authenticationService.authenticationService(this.username, this.password).subscribe({
     //   next: (data) => {
     //     this.invalidLogin = false;
     //     this.loginSuccess = true;
     //     this.successMessage = 'Login Successful.';
     //     // this.loginService.setUserId(data.id)
     //     this.router.navigate(['/person'/*+ data.id*/]);
     //   },
     //   error: () => {
     //     this.invalidLogin = true;
     //     this.loginSuccess = false;
     //   },
     //   complete: () => console.info('complete')
     // });

    this.authenticationService.authenticationService(/*this.username, this.password*/this.loginForm ).subscribe((data:Login)=> {
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.loginStatus = "true";
      this.successMessage = 'Login Successful.';
      this.authenticationService.setUserId(data.id);
      this.loginForm.user_id = data.id;
      console.log(data.id);
      this.router.navigate(['person/' + data.id]);
     }, () => {
       this.invalidLogin = true;
       this.loginSuccess = false;
     });

  }



    getStatus() {
    return this.loginStatus;
  }

}
