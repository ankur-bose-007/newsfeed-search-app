import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { User } from 'src/Model/User';
import { LoginService } from './login.service';
import { Router } from '@angular/router';
import { GlobalService } from 'src/GlobalServices/global.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginFormGroup=this.fb.group({
    emailId:['',[Validators.required,Validators.pattern('^([a-zA-Z0-9_\.-]+)@([a-zA-Z0-9_\.-]+)\\.([a-zA-Z]{2,5})$')]],
    password:['',Validators.required]
  });

  constructor(private fb:FormBuilder,private loginService:LoginService,private router:Router,private globalService:GlobalService) { }

  ngOnInit() {
    
  }

  get formValidations(){return this.loginFormGroup.controls}

  onLogin(user:User){
    this.loginService.login(user).subscribe(response=>{
    if(response.status==200){
      alert("Successfully logged in");
      this.globalService.jwt=response.body;
      localStorage.setItem('token',this.globalService.jwt);
      localStorage.setItem('id',user.emailId);
      this.loginService.getUserDetails(user.emailId).subscribe(response=>{
        
        this.globalService.user=response.body;
        
        
        if(response.body.admin){
          this.router.navigate(['Pageadmin']);
        }
        else
          this.router.navigate(['navbar']);
      });
    }
    },error=>{
      if(error.status==400)
        alert("User doesn't exist");
      else if(error.status==406)
        alert("Wrong credentials");
      else
        alert('Something went wrong')
    });
  }
}
