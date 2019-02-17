import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { UserLandingComponent } from './user-landing/user-landing.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SignupService } from './signup/signup.service';
import { AuthInterceptor } from './http-interceptors/auth-interceptor';
import { SearchHistoryComponent } from './search-history/search-history.component';
import { AdminComponent } from './admin/admin.component';
import { JwtModule, JwtHelperService } from '@auth0/angular-jwt';
import { AuthGuardService } from './Auth/auth-guard.service';
export function tokenGetter() {
  return sessionStorage.getItem('token');
}
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    UserLandingComponent,
    SearchHistoryComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    JwtModule.forRoot({config:{
      tokenGetter: tokenGetter
    }})
  ],
  providers: [AuthGuardService,SignupService,{provide: HTTP_INTERCEPTORS,useClass: AuthInterceptor,multi: true},JwtHelperService],
  bootstrap: [AppComponent]
})
export class AppModule { }
