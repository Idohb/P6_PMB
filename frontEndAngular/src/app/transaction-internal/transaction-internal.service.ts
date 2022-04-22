import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {TransactionInternal} from "./transactionInternal";
import {Observable} from "rxjs";
import {LoginService} from "../login-component/login/login.service";
import {PersonService} from "../person/person.service";
import {Person} from "../person/Person";
import {NgForm} from "@angular/forms";
import {Login} from "../login-component/login/login";

@Injectable({
  providedIn: 'root'
})
export class TransactionInternalService {
  private apiServerUrl = environment.apiBaseUrl;


  constructor(private http:HttpClient,
              private loginService : LoginService) { }




  public getTransactionInternal() : Observable<TransactionInternal[]>{
    return this.http.get<TransactionInternal[]>("http://localhost:8080/transactionInternalByCrediteur/" + this.loginService.getUserId());
  }

  public searchPersonConnection(personConnection : string) : Observable<Login> {
    return this.http.get<Login>("http://localhost:8080/loginSearch?emailLogin="+ personConnection + "&crediteur=" + this.loginService.getUserId());
  }

  public addPersonConnection(crediteur : number, debiteur : number){
    return this.http.get("http://localhost:8080/addFriends?crediteur=" + crediteur + "?debiteur=" + debiteur);
  }

}
