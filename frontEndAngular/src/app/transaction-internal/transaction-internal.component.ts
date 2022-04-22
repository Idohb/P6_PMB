import { Component, OnInit, Input} from '@angular/core';
import {TransactionInternal} from "./transactionInternal";
import {TransactionInternalService} from "./transaction-internal.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {Person} from "../person/Person";
import {LoginService} from "../login-component/login/login.service";

@Component({
  selector: 'app-transaction-internal',
  templateUrl: './transaction-internal.component.html',
  styleUrls: ['./transaction-internal.component.scss']
})
export class TransactionInternalComponent implements OnInit {

  public transactionInternalResponse: TransactionInternal[] = [];
  public transactionInternalForm : TransactionInternal = new class implements TransactionInternal {
    amount: string= "";
    crediteur!: Person;
    debiteur!: Person;
    description: string= "";
    emailLogin: string= "";
    idTransaction: number= 0;
    timeTransaction: string= "";
  }
  private email :string ="";
  private debiteurId: number= 0;

  constructor(private transactionInternalService: TransactionInternalService,
              private loginService:LoginService,
              private router : Router) { }

  ngOnInit(): void {
    this.transactionInternalService.getTransactionInternal().subscribe( {
      next: (data) => {
        this.transactionInternalResponse = data;
      },
      error : () => {
        console.info('error transaction')
      },
      complete: () => console.info('show transaction complete')


      }

    );
  }



  public onOpenModal(person: Person|null, mode: string): void {
    const container = document.getElementById('main-container');
    if (container === null) { return;}
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addPersonConnectionModal');
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
