import { Component, OnInit } from '@angular/core';
import { GlobalService } from 'src/GlobalServices/global.service';
import { SearchService } from '../user-landing/search.service';
import { Search } from 'src/Model/Search';

@Component({
  selector: 'app-search-history',
  templateUrl: './search-history.component.html',
  styleUrls: ['./search-history.component.css']
})
export class SearchHistoryComponent implements OnInit {

  searchHistoryList: Array<Search>;

  constructor(private globalService: GlobalService, private searchService: SearchService) {
    this.searchHistoryList = new Array<Search>();
  }

  ngOnInit() {
    this.searchService.fetchAllSearches(localStorage.getItem('id')).subscribe(response => {
      if (response.status == 200)
        this.searchHistoryList = response.body;
    }, error => {
      alert("Not found");
    });
  }

  delete(search: Search) {
    this.searchService.deleteSearch(search, this.globalService.user.emailId).subscribe(response => {
      if (response.status == 200) {
        this.searchHistoryList.splice(this.searchHistoryList.indexOf(search), 1);
        alert("record deleted");
      }
    }, error => {
      alert("Try again");
    });
  }
}
