import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/Model/User';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }

  searchUser(emailId:string){
    return this.http.get<Array<User>>('admin/searchUser/'+emailId,{observe:'response'});
  }

  searchAllUsers(){
    return this.http.get<Array<User>>('admin/searchAllUsers',{observe:'response'});
  }

  blacklist(emailId:string){
    return this.http.get('admin/blacklist/'+emailId,{observe:'response',responseType:'text'});
  }
}
