import { Component, OnInit } from '@angular/core';
import { FormBuilder,Validators } from '@angular/forms';
import { User } from 'src/Model/User';
import { SignupService } from './signup.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signUpFormGroup=this.fb.group({
    emailId:['',[Validators.required,Validators.pattern('^([a-zA-Z0-9_\.-]+)@([a-zA-Z0-9_\.-]+)\\.([a-zA-Z]{2,5})$')]],
    password:['',[Validators.required,Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&]).{8,}$')]],
    userName:['',[Validators.required]]
  });

  constructor(private fb:FormBuilder,private signupService:SignupService,private router:Router) { }

  ngOnInit() {
  }

  get formValidations(){return this.signUpFormGroup.controls}

  onSubmit(user:User){
    this.signupService.signup(user).subscribe(response=>{
      if(response.status==201){
        alert('User registered Successfully');
        this.router.navigate(['/']);
      }
    },error=>{
      if(error.status==409)
        alert('User already exists. Please Login using credentials.');
      else
        alert('Wrong details');
    });
  }
}
