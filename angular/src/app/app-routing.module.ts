import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { UserLandingComponent } from './user-landing/user-landing.component';
import { SearchHistoryComponent } from './search-history/search-history.component';
import { AdminComponent } from './admin/admin.component';
import { AuthGuard } from './Auth/auth.guard';


const routes: Routes = [
  {path:'',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'Pageuser',component:UserLandingComponent,canActivate: [AuthGuard]},
  {path:'searchHistory',component:SearchHistoryComponent,canActivate: [AuthGuard]},
  {path:'Pageadmin',component:AdminComponent,canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
