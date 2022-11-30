import { Component, OnInit} from '@angular/core';
import {TransactionInternalRequest, TransactionInternal} from "./transactionInternal";
import {TransactionInternalService} from "./transaction-internal.service";
import {Person} from "../person/Person";
import {LoginService} from "../login-component/login/login.service";
import {TransactionExternalService} from "../transaction-external/transaction-external.service";
import {AuthenticationService} from "../login-component/login/auth.service";
import {TransactionExternal} from "../transaction-external/transactionExternal";

@Component({
  selector: 'app-transaction-internal',
  templateUrl: './transaction-internal.component.html',
  styleUrls: ['./transaction-internal.component.scss']
})
export class TransactionInternalComponent implements OnInit {

  public transactionInternalResponse: TransactionInternal[] = [];
  public transactionInternalForm : TransactionInternal = new class implements TransactionInternal {
    amount: string= "";
    crediteur: number = 0;
    debiteur: number = 0;
    description: string = "";
    emailLogin: string = "";
    idTransaction: number = 0;
    timeTransaction: string= "";
  }

  public listFriends: Person[] = [];
  public friends : Person = new class implements Person {
    firstName: string = "";
    id: number = 0;
    lastName: string = "";
  };

  public transactionInternalRequest: TransactionInternalRequest[] = [];
  public transactionInternalRequestForm: TransactionInternalRequest = new class implements TransactionInternalRequest {
    amount: string= "";
    description: string = "";
    emailLogin: string = "";
    idTransaction: number = 0;
    timeTransaction: string= "";
    crediteur!: Person;
    debiteur!: Person;

  }


  private email :string ="";
  private debiteurId: number= 0;


  constructor(private transactionInternalService: TransactionInternalService,
              private transactionExternalService: TransactionExternalService,
              private loginService:LoginService,
              private authenticationService : AuthenticationService) { }

  ngOnInit(): void {
    this.getTransactions();
    this.transactionExternalService.getTransactionExternal();
    this.getFriends();
  }

  private getFriends() {
    this.transactionInternalService.getFriends().subscribe( {
      next: (data) => {
        this.listFriends = data;
      },
      error : () => {
        console.info('error retrieving friends')
      }
    });
  }

  private getTransactions() {
    this.transactionInternalService.getTransactionInternal().subscribe( {
        next: (data) => {
          this.transactionInternalRequest = data;
        },
        error : () => {
          console.info('error transaction')
        },
        complete: () => console.info('show transaction complete')
      }
    );
  }


  public clickToPay(payForm: TransactionInternal) : void {
    payForm.crediteur = this.authenticationService.getUserId();
    payForm.description = payForm.amount + " euros";
    console.log(payForm.debiteur);

    //TODO : est ce que le subscribe est utile ? + regarde la manipulation de données localstorage ou cookies
    this.transactionInternalService.setAmount(payForm).subscribe( {
      next:() => {
        this.getTransactions();
        this.transactionExternalService.getTransactionExternal();

      },
      error:() => {
        console.info("error ");
      }
    });

  }

  public onOpenModalToAddFriends(person: Person|null, mode: string): void {
    const container = document.getElementById('main-container');
    if (container === null) { return;}
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addPersonConnectionModal');
    }
    if (mode === 'pay') {
      button.setAttribute('data-target', '#addPersonConnectionModal')
    }
    container.appendChild(button);
    button.click();
  }

  public onAddPersonConnection(addForm: TransactionInternal): void {
    const elementAddPerson = document.getElementById('add-employee-form');
    this.email = addForm.emailLogin;
    if (elementAddPerson === null) {
      alert('oops');
    } else {
      elementAddPerson.click();
    }
    console.log(addForm.emailLogin);
    this.addPersonConnection(this.email);
  }

  public addPersonConnection(personConnection : string) {
    console.log(personConnection);
    // la méthode addPersonConnection a pour but de chercher une personne par email.
    this.transactionInternalService.searchPersonConnection(personConnection).subscribe( {
      next:(debiteur) => {
        // console.log(this.loginService.getUserId());
        console.log(debiteur.id);
        this.setDebiteurId(debiteur.id);
        this.getFriends();
      },
      error:() => {
        console.info("error ");
      }
    });
    this.transactionInternalService.addPersonConnection(this.loginService.getUserId(), this.getDebiteurId());
  }

  private setDebiteurId(debiteurId:number): void {
    this.debiteurId = debiteurId;
  }

  public getDebiteurId() : number {
    return this.debiteurId;
  }


}
