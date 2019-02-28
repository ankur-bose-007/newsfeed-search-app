import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from './admin.service';
import { User } from 'src/Model/User';
import { FormBuilder } from '@angular/forms';
import { GlobalService } from 'src/GlobalServices/global.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  listOfUsers: Array<User>;

  SearchForm = this.fb.group({
    search: ['']
  });
  constructor(private router: Router, private adminService: AdminService, private fb: FormBuilder, private globalService: GlobalService) {
    this.listOfUsers = new Array<User>();
  }

  ngOnInit() {
    this.adminService.searchAllUsers().subscribe(response => {

      this.listOfUsers = response.body;
    }, error => {
      if (error.status == 403) {
        localStorage.removeItem('token');
        this.router.navigate(['']);
      }
      else
        alert('No record found')
    });
  }

  blacklist(user: User) {
    this.adminService.blacklist(user.emailId).subscribe(response => {
      if (response.status == 200) {
        this.listOfUsers[this.listOfUsers.indexOf(user)].active = false;
        alert('User Blacklisted');
      }
    }, error => {
      alert('Something went wrong');
    });
  }

  LogOut() {
    localStorage.removeItem('token');
    this.globalService.jwt = '';
    this.router.navigate(['/']);
  }

  searchUser(search: any) {
    if (search.search == "") {
      this.adminService.searchAllUsers().subscribe(response => {
        this.listOfUsers = response.body;
      }, error => {
        alert('No record found')
      });
    } else {
      this.adminService.searchUser(search.search).subscribe(response => {
        if (response.status == 200)
          this.listOfUsers = response.body;
        if (response.body == null)
          alert('No record found');
      }, error => {
        alert('something went wrong');
      });
    }
  }
}
