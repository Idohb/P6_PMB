import { Component, OnInit } from '@angular/core';
import {TransactionExternalRequest, TransactionExternal} from "../transaction-external/transactionExternal";
import {TransactionExternalService} from "./transaction-external.service";
import {LoginService} from "../login-component/login/login.service";

@Component({
  selector: 'app-transaction-external',
  templateUrl: './transaction-external.component.html',
  styleUrls: ['./transaction-external.component.scss']
})
export class TransactionExternalComponent implements OnInit {

  public transactionExternalResponse: TransactionExternal[] = [];
  public transactionExternalForm : TransactionExternal  = new class implements TransactionExternal {
    user: number = 0;
    description: string= "";
    amount: string= "";
    timeTransaction: string= "";
  }

  public transactionExternalRequest: TransactionExternal[] = [];
  public transactionExternalRequestForm : TransactionExternal  = new class implements TransactionExternalRequest {
    user: number = 0;
    description: string= "";
    amount: string= "";
    timeTransaction: string= "";
  }

  constructor(private transactionExternalService: TransactionExternalService,
              private loginService: LoginService) { }

  private getTransactions() {
    this.transactionExternalService.getTransactionExternal().subscribe( {
        next: (data) => {
          this.transactionExternalRequest = data;
        },
        error : () => {
          console.info('error transaction')
        },
        complete: () => console.info('show transaction complete')
      }
    );
  }


  ngOnInit(): void {
    this.getTransactions();
  }

}
