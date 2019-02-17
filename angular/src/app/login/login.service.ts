import { Injectable } from '@angular/core';
import { User } from 'src/Model/User';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  login(user:User){
      return this.http.post('user/login',user,{responseType:'text',observe:'response'});
  }

  getUserDetails(emailId:string){
    return this.http.get<User>('user/getUserDetails/'+emailId,{observe:'response'});
  }
}
