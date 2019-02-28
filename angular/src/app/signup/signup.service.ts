import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/Model/User';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private http: HttpClient) { }

  signup(user: User) {
    return this.http.post('/newsfeedservice/user/signup', user, { responseType: 'text', observe: 'response' });
  }
}
