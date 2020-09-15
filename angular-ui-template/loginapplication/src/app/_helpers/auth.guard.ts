import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private routes : Router, private userEntry: AuthService){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot):  boolean {
      if(localStorage.getItem('token') === this.userEntry.token){
        return true;
      }
      else
      {
        this.routes.navigate(['/login']);
        return false;
      }
  }

}
