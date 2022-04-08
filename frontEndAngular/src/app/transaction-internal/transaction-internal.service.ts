import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {TransactionInternal} from "./transactionInternal";
import {Observable} from "rxjs";
import {LoginService} from "../login-component/login/login.service";

@Injectable({
  providedIn: 'root'
})
export class TransactionInternalService {
  private apiServerUrl = environment.apiBaseUrl;


  constructor(private http:HttpClient, private loginService : LoginService) { }

  public getTransactionInternal() : Observable<TransactionInternal[]>{
    return this.http.get<TransactionInternal[]>("http://localhost:8080/transactionInternalByCrediteur/" + this.loginService.getUserId());
  }

}
