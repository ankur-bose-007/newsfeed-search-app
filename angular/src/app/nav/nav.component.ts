import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalService } from 'src/GlobalServices/global.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  constructor(private router: Router, private globalService: GlobalService) { }

  ngOnInit() {
    this.router.navigate(['navbar/Pageuser']);
  }
  history() {
    this.router.navigate(['navbar/searchHistory']);
  }
  news() {
    this.router.navigate(['navbar/Pageuser']);
  }
  LogOut() {
    localStorage.removeItem('token');
    this.globalService.jwt = '';
    this.router.navigate(['/']);
  }
}