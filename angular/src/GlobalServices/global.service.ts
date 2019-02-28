import { Injectable } from '@angular/core';
import { User } from 'src/Model/User';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {
  user:User;
  jwt:string;
  constructor() { }
}
