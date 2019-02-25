import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupComponent } from './signup.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { SignupService } from './signup.service';
import { DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';
import { of } from 'rxjs/internal/observable/of';
import { User } from 'src/Model/User';
import { Observable } from 'rxjs';

describe('SignupComponent', () => {
  let component: SignupComponent;
  let fixture: ComponentFixture<SignupComponent>;
  let signupService:SignupService;
  let debugElement:DebugElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignupComponent ],
      imports:[ReactiveFormsModule,HttpClientModule,RouterTestingModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignupComponent);
    component = fixture.componentInstance;
    debugElement=fixture.debugElement;
    signupService=debugElement.injector.get(SignupService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  it('blank email',()=>{
    let emailId=component.signUpFormGroup.get('emailId');
    emailId.setValue('');
    expect(component.signUpFormGroup.controls.emailId.errors.required).toBeTruthy();
  });


  it('wrong email required',()=>{
    let emailId=component.signUpFormGroup.get('emailId');
    emailId.setValue('dsadsa');
    expect(component.signUpFormGroup.controls.emailId.errors.pattern).toBeTruthy();
  });

  it('blank password',()=>{
    let password=component.signUpFormGroup.get('password');
    password.setValue('');
    expect(component.signUpFormGroup.controls.password.errors.required).toBeTruthy();
  });

  it('wrong password pattern',()=>{
    let password=component.signUpFormGroup.get('password');
    password.setValue('dadasdsd');
    expect(component.signUpFormGroup.controls.password.errors.pattern).toBeTruthy();
  });

  it('username required',()=>{
    let userName=component.signUpFormGroup.get('userName');
    userName.setValue('');
    expect(component.signUpFormGroup.controls.userName.errors.required).toBeTruthy();
  });
  it('user successfully registered',()=>{
    let res:any;
    let user:User=new User();
    spyOn(signupService,'signup').and.returnValue(of({status:201}));
    let spy=spyOn(signupService,'signup').and.callThrough();
    debugElement.query(By.css('form')).triggerEventHandler('submit',null);
    signupService.signup(user).subscribe(data=>{
      res=data;
    });
    expect(res).toEqual({status:201});
  });
});
