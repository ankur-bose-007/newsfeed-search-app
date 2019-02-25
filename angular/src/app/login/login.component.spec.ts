import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { DebugElement } from '@angular/core';
import { LoginService } from './login.service';
import { of } from 'rxjs/internal/observable/of';
import { By } from '@angular/platform-browser';
import { User } from 'src/Model/User';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let debugElement:DebugElement;
  let loginService:LoginService;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports:[ ReactiveFormsModule ,HttpClientModule, RouterTestingModule],
      providers:[LoginService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    debugElement=fixture.debugElement;
    loginService=debugElement.injector.get(LoginService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('email blank',()=>{
    let emailId=component.loginFormGroup.get('emailId');
    emailId.setValue('');

    expect(component.loginFormGroup.controls.emailId.errors.required).toBeTruthy();
  });

  it('email pattern wrong',()=>{
    let emailId=component.loginFormGroup.get('emailId');
    emailId.setValue('dsadaa');

    expect(component.loginFormGroup.controls.emailId.errors.pattern).toBeTruthy();
  });

  it('password empty',()=>{
    let password=component.loginFormGroup.get('password');
    password.setValue('');

    expect(component.loginFormGroup.controls.password.errors.required).toBeTruthy();
  });

  it('successfully registered',()=>{
    let response:any;

    spyOn(loginService,'login').and.returnValue(of({status:200}));
    let user:User=new User();
    loginService.login(user).subscribe(data=>{
      response=data;
    });

    debugElement.query(By.css('form')).triggerEventHandler('submit',null);
    expect(response).toEqual({status:200});
  })
});
