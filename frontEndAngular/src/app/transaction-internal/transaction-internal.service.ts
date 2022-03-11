import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {TransactionInternal} from "./transactionInternal";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TransactionInternalService {
  private apiServerUrl = environment.apiBaseUrl;


  constructor(private http:HttpClient) { }

  public getTransactionInternal() {
    return this.http.get<TransactionInternal>("http://localhost:8080/transactionInternalByCrediteur/2");
  }
}
