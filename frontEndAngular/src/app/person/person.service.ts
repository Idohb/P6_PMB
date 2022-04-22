import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Login} from "../login-component/login/login";
import {Observable} from "rxjs";
import {Person} from "./Person";

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http:HttpClient) { }

  // public getPerson(): Observable<Person> {
  //   return this.http.get<Person>(apiServerUrl + "Person" + login.email + "&password=" + login.password);
  // }

  public addPerson(person : Person) : Observable<Person> {
    return this.http.post<Person>(`${this.apiServerUrl}/person/add`, person);
  }

  public updatePerson(person : Person) : Observable<Person> {
    return this.http.put<Person>(`${this.apiServerUrl}/person/update`, person);
  }

  public deletePerson(personId : number) : Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/person/${personId}`);
  }


}
