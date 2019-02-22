import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { UserLandingComponent } from './user-landing/user-landing.component';
import { SearchHistoryComponent } from './search-history/search-history.component';
import { AdminComponent } from './admin/admin.component';
import { AuthGuard } from './Auth/auth.guard';
import { NavComponent } from './nav/nav.component';


const routes: Routes = [
  {path:'',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'navbar',component:NavComponent,canActivate: [AuthGuard],
  children:[
  {path:'Pageuser',component:UserLandingComponent},
  {path:'searchHistory',component:SearchHistoryComponent}
  ]},
  {path:'Pageadmin',component:AdminComponent,canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
