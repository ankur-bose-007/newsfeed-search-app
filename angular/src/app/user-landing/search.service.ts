import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Search } from 'src/Model/Search';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  
  constructor(private http:HttpClient) {}

  addSearch(search:Search,userEmail:string){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.put('user/addSearch/'+userEmail,search,{headers,responseType:'text',observe:'response'});
  }
  fetchAllSearches(emailId:string){
    return this.http.get<Array<Search>>('user/searchHistory/'+emailId,{observe:'response'});
  }
  deleteSearch(search:Search,userEmail:string){
    return this.http.delete('user/deleteSearch/'+userEmail+'/'+search.id,{responseType:'text',observe:'response'});
  }
}
