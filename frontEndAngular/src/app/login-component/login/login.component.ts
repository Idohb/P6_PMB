import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  @Input() loginName : string = "loginNameFromLoginComponent";
  loginTitle : string = "Login";

  constructor() { }

  ngOnInit(): void {
  }

}
