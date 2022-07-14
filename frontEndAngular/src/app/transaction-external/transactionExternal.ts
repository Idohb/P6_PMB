import {Person} from "../person/Person";

export interface TransactionExternal {
  user: number;
  description: string;
  amount: string;
  timeTransaction: string;
}

export interface TransactionExternalRequest {
  user: number;
  description: string;
  amount: string;
  timeTransaction: string;
}

