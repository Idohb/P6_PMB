import {Person} from "../person/Person";

export interface TransactionInternal {
  idTransaction: number;
  description: string;
  amount: string;
  timeTransaction: string;
  crediteur : number ;
  debiteur: number;
  emailLogin:string;
}

export interface TransactionInternalRequest {
  idTransaction: number;
  description: string;
  amount: string;
  timeTransaction: string;
  crediteur : Person ;
  debiteur: Person;
  emailLogin:string;
}

