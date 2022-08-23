import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login-component/login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes} from "@angular/router";
import { PersonComponent } from './person/person.component';
import { TransactionInternalComponent } from './transaction-internal/transaction-internal.component';
import { TransactionExternalComponent } from './transaction-external/transaction-external.component';
import { LogoutComponent } from './logout/logout.component';

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'person', component: LoginComponent },
  { path: 'transaction-internal', component:TransactionInternalComponent},
  { path: 'transaction-external', component:TransactionExternalComponent},
  { path: 'person/:id', component: PersonComponent },
  { path: '', component: LoginComponent }
];
// reactiveFormModule
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PersonComponent,
    TransactionInternalComponent,
    TransactionExternalComponent,
    LogoutComponent,
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
