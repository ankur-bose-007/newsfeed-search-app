import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  api_key = 'b71647f480fc406fa194dd902705daf2';
  constructor(private http: HttpClient) { }
  initSources() {
    return this.http.get('https://newsapi.org/v2/sources?language=en&apiKey=' + this.api_key);
  }
  initArticles() {
    return this.http.get('https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=' + this.api_key);
  }
  getArticlesByID(source: string) {
    return this.http.get('https://newsapi.org/v2/everything?q=' + source + '& from=2019-02-16&' +
      'sortBy=popularity&' + 'apiKey=' + this.api_key);
  }
}
