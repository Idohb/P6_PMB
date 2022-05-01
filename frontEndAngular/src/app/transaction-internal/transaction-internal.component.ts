import { Component, OnInit, Input} from '@angular/core';
import {TransactionInternalRequest, TransactionInternal} from "./transactionInternal";
import {TransactionInternalService} from "./transaction-internal.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {Person} from "../person/Person";
import {LoginService} from "../login-component/login/login.service";
import {PersonService} from "../person/person.service";
import {LoginComponent} from "../login-component/login/login.component";

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

  public transactionInternalObject !: TransactionInternal;

  private email :string ="";
  private debiteurId: number= 0;
  private debiteurInfoTest!:Person;

  constructor(private transactionInternalService: TransactionInternalService,
              private loginService:LoginService,
              private personService:PersonService,
              private router : Router) { }

  ngOnInit(): void {
    this.getTransactions();
  }

  private getTransactions() {
    this.transactionInternalService.getTransactionInternal().subscribe( {
        next: (data) => {
          // this.transactionInternalResponse = data;
          this.transactionInternalRequest = data;
          for (const dataKey in data) {
            console.log(data[dataKey].debiteur);
          }
        },
        error : () => {
          console.info('error transaction')
        },
        complete: () => console.info('show transaction complete')
      }
    );
  }


  public clickToPay(payForm: TransactionInternal) : void {
    payForm.crediteur = this.loginService.getUserId();
    payForm.description = payForm.amount + " euros";
    payForm.debiteur = 3;

    this.transactionInternalService.setAmount(payForm).subscribe( {
      next:(debiteur) => {
        this.getTransactions();
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
        console.log(debiteur.idLogin);
        this.setDebiteurId(debiteur.idLogin);
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

  public getDebiteurId() : number{
    return this.debiteurId;
  }


}
