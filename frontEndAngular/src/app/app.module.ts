import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login-component/login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {RouterModule, Routes} from "@angular/router";
import { PersonComponent } from './person/person.component';
import {LoginService} from "./login-component/login/login.service";
import { TransactionInternalComponent } from './transaction-internal/transaction-internal.component';

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'person', component: LoginComponent },
  { path: 'person/:id', component: PersonComponent },
  { path: '', component: LoginComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PersonComponent,
    TransactionInternalComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
