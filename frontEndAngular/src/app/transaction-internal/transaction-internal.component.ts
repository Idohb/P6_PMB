import { Component, OnInit } from '@angular/core';
import {TransactionInternal} from "./transactionInternal";
import {TransactionInternalService} from "./transaction-internal.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-transaction-internal',
  templateUrl: './transaction-internal.component.html',
  styleUrls: ['./transaction-internal.component.scss']
})
export class TransactionInternalComponent implements OnInit {

  public transactionInternalResponse: TransactionInternal[] = [];
  public transactionInternals: TransactionInternal = new class implements TransactionInternal {
    idTransaction: number = 0;
    Description: string = "";
    Amount: string = "";
    TimeTransaction: string = "";
    Crediteur : string = "";
    Debiteur: string = "";
  };



  constructor(private transactionInternalService: TransactionInternalService, private router : Router) { }

  ngOnInit(): void {
    this.transactionInternalService.getTransactionInternal().subscribe( {
      next: (data) => {
        console.log(data.Description);
      },
      error : () => {
        console.info('error transaction')
      },
      complete: () => console.info('show transaction complete')



      }

    );
  }

}
