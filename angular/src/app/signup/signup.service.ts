import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/Model/User';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private http: HttpClient) { }

<<<<<<< HEAD
  signup(user: User) {
    return this.http.post('/newsfeedservice/user/signup', user, { responseType: 'text', observe: 'response' });
=======
  signup(user:User){
    return this.http.post('/newsfeedservice/user/signup',user,{responseType:'text',observe:'response'});
>>>>>>> 01742941f0c109de7f0a83dc40d1d8ca78acad57
  }
}
