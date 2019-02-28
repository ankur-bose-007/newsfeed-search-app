import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Search } from 'src/Model/Search';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  addSearch(search: Search, userEmail: string) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
<<<<<<< HEAD
    return this.http.put('/newsfeedservice/user/addSearch/' + userEmail, search, { headers, responseType: 'text', observe: 'response' });
  }
  fetchAllSearches(emailId: string) {
    return this.http.get<Array<Search>>('/newsfeedservice/user/searchHistory/' + emailId, { observe: 'response' });
  }
  deleteSearch(search: Search, userEmail: string) {
    return this.http.delete('/newsfeedservice/user/deleteSearch/' + userEmail + '/' + search.id, { responseType: 'text', observe: 'response' });
=======
    return this.http.put('/newsfeedservice/user/addSearch/'+userEmail,search,{headers,responseType:'text',observe:'response'});
  }
  fetchAllSearches(emailId:string){
    return this.http.get<Array<Search>>('/newsfeedservice/user/searchHistory/'+emailId,{observe:'response'});
  }
  deleteSearch(search:Search,userEmail:string){
    return this.http.delete('/newsfeedservice/user/deleteSearch/'+userEmail+'/'+search.id,{responseType:'text',observe:'response'});
>>>>>>> 01742941f0c109de7f0a83dc40d1d8ca78acad57
  }
}
