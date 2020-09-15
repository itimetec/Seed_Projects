import { environment } from './../../../environments/environment';
import { AuthService } from './../../services/auth.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  loginForm: FormGroup;
  enteredUserDetails: any;
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  login() {
    const value = this.loginForm.value;
    if (value.userName && value.password) {
      if (
        value.userName == environment.ADMIN_USER_NAME &&
        value.password == environment.ADMIN_PASSWORD
      ) {
        this.storeAdmin();
        return true;
      } else {
        this.authService.login().subscribe((userCredentials) => {
          this.enteredUserDetails = userCredentials;
          let currentUser = this.enteredUserDetails.find(
            (currentUser) =>
              currentUser.userName === value.userName &&
              currentUser.password === value.password
          );
          if (currentUser) {
            this.storeUser(currentUser.token);
            return true;
          } else {
            return false;
          }
        });
      }
    }
  }
  storeUser(token: string) {
    localStorage.setItem('token', token);
    this.authService.token = token;
    this.router.navigateByUrl('/home');
  }
  storeAdmin() {
    const token = environment.TOKEN;
    this.authService.token = token;
    localStorage.setItem('token', token);
    this.router.navigateByUrl('/dashboard');
  }
}
