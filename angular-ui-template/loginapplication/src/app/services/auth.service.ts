import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {commonService} from "./service_abstract";
@Injectable({
  providedIn: 'root'
})
export class AuthService extends commonService{

  constructor(private http: HttpClient) {
    super(http);
  }
  token: string = "";

  login() {
    return this.http.get(environment.API_PREFIX);
  }
  postUserDetails(userData) {
    this.http.post<any>(environment.API_PREFIX,userData).subscribe();
  }
}
