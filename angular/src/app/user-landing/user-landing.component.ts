import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/GlobalServices/global.service';
import { Router } from '@angular/router';
import { ApiService } from './api.service';
import { FormBuilder } from '@angular/forms';
import { SearchService } from './search.service';
import { Search } from 'src/Model/Search';

@Component({
  selector: 'app-user-landing',
  templateUrl: './user-landing.component.html',
  styleUrls: ['./user-landing.component.css']
})
export class UserLandingComponent implements OnInit {

  mArticles:Array<any>;
  mSources:Array<any>;
  search:Search;
  SearchForm=this.fb.group({
    search:['']
  });

  constructor(private router:Router,private globalService:GlobalService,private newsapi:ApiService,private fb:FormBuilder,private searchService:SearchService) { 

  }

  ngOnInit() {
    this.search=new Search();
    this.newsapi.initArticles().subscribe(data => this.mArticles = data['articles']);
    //load news sources
    this.newsapi.initSources().subscribe(data=> this.mSources = data['sources']);  
    }


  searchArticles(source:any){
    this.search.keyword=source.search;
    this.searchService.addSearch(this.search,this.globalService.user.emailId).subscribe(response=>{
      if(response.status==200)
        alert("search added");
    });
    this.newsapi.getArticlesByID(source.search).subscribe(data => this.mArticles = data['articles']);
  }

  LogOut(){
    localStorage.removeItem('token');
    this.globalService.jwt='';
    this.router.navigate(['/']);
  }

  history(){
    this.router.navigate(['searchHistory']);
  }

}
