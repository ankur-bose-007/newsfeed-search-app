import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { UserLandingComponent } from './user-landing/user-landing.component';
import { SearchHistoryComponent } from './search-history/search-history.component';
import { AdminComponent } from './admin/admin.component';
import { AuthGuardService } from './Auth/auth-guard.service';

const routes: Routes = [
  {path:'',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'userlandingPage',component:UserLandingComponent,canActivate: [AuthGuardService]},
  {path:'searchHistory',component:SearchHistoryComponent,canActivate: [AuthGuardService]},
  {path:'adminlandingPage',component:AdminComponent,canActivate: [AuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
