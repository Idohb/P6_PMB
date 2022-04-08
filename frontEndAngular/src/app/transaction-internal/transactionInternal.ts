import {Person} from "../person/Person";

export interface TransactionInternal {
  idTransaction: number;
  description: string;
  amount: string;
  timeTransaction: string;
  crediteur : Person ;
  debiteur: Person;
}
