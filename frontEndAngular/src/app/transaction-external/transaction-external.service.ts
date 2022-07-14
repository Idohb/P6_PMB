import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {PersonService} from "../person/person.service";
import {LoginService} from "../login-component/login/login.service";
import {TransactionExternalRequest, TransactionExternal} from "./transactionExternal";
import {Observable} from "rxjs";
import {Login} from "../login-component/login/login";
import {Person} from "../person/Person";

@Injectable({
  providedIn: 'root'
})
export class TransactionExternalService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http:HttpClient,
              private personService : PersonService,
              private loginService : LoginService) { }

  public getTransactionExternal(): Observable<TransactionExternalRequest[]> {
    return this.http.get<TransactionExternalRequest[]>(this.apiServerUrl + "transactionExternalByUser/" + this.loginService.getUserId());
  }

  public searchPersonConnection(personConnection : string) : Observable<Login> {
    return this.http.get<Login>(this.apiServerUrl + "loginSearch?emailLogin="+ personConnection + "&crediteur=" + this.loginService.getUserId());
  }

  public addPersonConnection(crediteur : number, debiteur : number) : Observable<Person>{
    return this.http.get<Person>(this.apiServerUrl + "addFriends?crediteur=" + crediteur + "?debiteur=" + debiteur);
  }

  public setAmount(amount : TransactionExternal) : Observable<TransactionExternal> {
    return this.http.post<TransactionExternal>(this.apiServerUrl + "transactionExternal", amount);
  }






}
