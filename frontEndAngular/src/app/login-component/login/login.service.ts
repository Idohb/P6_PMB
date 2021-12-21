import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Login} from "./login";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http:HttpClient) { }

  public getLogin(login : Login): Observable<Login> {
    return this.http.get<Login>("http://localhost:8080/login?email=" + login.email + "&password=" + login.password);
  }

}
