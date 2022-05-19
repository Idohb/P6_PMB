import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {TransactionInternalRequest, TransactionInternal} from "./transactionInternal";
import {Observable} from "rxjs";
import {LoginService} from "../login-component/login/login.service";
import {PersonService} from "../person/person.service";
import {Person} from "../person/Person";
import {Login} from "../login-component/login/login";

@Injectable({
  providedIn: 'root'
})
export class TransactionInternalService {
  private apiServerUrl = environment.apiBaseUrl;


  constructor(private http:HttpClient,
              private personService : PersonService,
              private loginService : LoginService) { }




  // public getTransactionInternal() : Observable<TransactionInternal[]>{
  //   return this.http.get<TransactionInternal[]>("http://localhost:8080/transactionInternalByCrediteur/" + this.loginService.getUserId());
  // }
  public getTransactionInternal() : Observable<TransactionInternalRequest[]>{
    return this.http.get<TransactionInternalRequest[]>(this.apiServerUrl + "transactionInternalByCrediteur/" + this.loginService.getUserId());
  }

  public searchPersonConnection(personConnection : string) : Observable<Login> {
    return this.http.get<Login>(this.apiServerUrl + "loginSearch?emailLogin="+ personConnection + "&crediteur=" + this.loginService.getUserId());
  }

  public addPersonConnection(crediteur : number, debiteur : number) : Observable<Person>{
    return this.http.get<Person>(this.apiServerUrl + "addFriends?crediteur=" + crediteur + "?debiteur=" + debiteur);
  }

  public setAmount(amount : TransactionInternal) : Observable<TransactionInternal> {
    return this.http.post<TransactionInternal>(this.apiServerUrl + "transactionInternal", amount);
  }

  public getFriends() : Observable<Person[]>{
    return this.http.get<Person[]>(this.apiServerUrl + "friends/" + this.loginService.getUserId());
  }
}
