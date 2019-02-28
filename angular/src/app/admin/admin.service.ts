import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/Model/User';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

<<<<<<< HEAD
  searchUser(emailId: string) {
    return this.http.get<Array<User>>('/newsfeedservice/admin/searchUser/' + emailId, { observe: 'response' });
  }

  searchAllUsers() {
    return this.http.get<Array<User>>('/newsfeedservice/admin/searchAllUsers', { observe: 'response' });
  }

  blacklist(emailId: string) {
    return this.http.get('/newsfeedservice/admin/blacklist/' + emailId, { observe: 'response', responseType: 'text' });
=======
  searchUser(emailId:string){
    return this.http.get<Array<User>>('/newsfeedservice/admin/searchUser/'+emailId,{observe:'response'});
  }

  searchAllUsers(){
    return this.http.get<Array<User>>('/newsfeedservice/admin/searchAllUsers',{observe:'response'});
  }

  blacklist(emailId:string){
    return this.http.get('/newsfeedservice/admin/blacklist/'+emailId,{observe:'response',responseType:'text'});
>>>>>>> 01742941f0c109de7f0a83dc40d1d8ca78acad57
  }
}
