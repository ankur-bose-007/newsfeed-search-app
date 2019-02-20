import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { GlobalService } from 'src/GlobalServices/global.service';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(private jwtHelper:JwtHelperService,private globalService:GlobalService) { }

  public isAuthenticated():boolean{
      const token = localStorage.token;
      return !this.jwtHelper.isTokenExpired(token);
  }
}
