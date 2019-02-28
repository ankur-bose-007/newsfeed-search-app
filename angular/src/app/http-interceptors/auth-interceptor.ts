import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    // Get the auth token from the service.
    const authToken = 'Bearer ' + localStorage.getItem('token');

    const authReq = req.clone({ setHeaders: { Authorization: authToken } });

    // send cloned request with header to the next handler.

    return next.handle(authReq);
  }
}