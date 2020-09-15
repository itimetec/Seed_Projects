import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import {Router}from '@angular/router';
import { FormBuilder, Validators, FormGroup } from "@angular/forms";
import {PasswordValidator} from './password_validator';
import randomString from 'randomstring';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  registerUserData: any[] = [];
  registrationForm: FormGroup;
  constructor(private fb: FormBuilder, private router: Router, private userEntry: AuthService) {}

  ngOnInit() {
    this.registrationForm = this.fb.group(
      {
        userName: ['', [Validators.required, Validators.minLength(4)]],
        email: ['', [Validators.required]],
        phoneNumber: ['',
          [Validators.required, Validators.maxLength(10)]
        ],
        password: ["", [Validators.required]],
        confirmPassword: [""],
        termsAndConditions: ["", [Validators.required]],
        token: [""]
      },
      { validator: PasswordValidator }
    );
  }

  get userName() {
    return this.registrationForm.get("userName");
  }
  get email() {
    return this.registrationForm.get("email");
  }
  get phoneNumber() {
    return this.registrationForm.get("phoneNumber");
  }
  get password() {
    return this.registrationForm.get("password");
  }
  randomString(length: number): string {
    const stringSet = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    let token = '';
    for( let i=length; i>0; i--){
      token += stringSet[Math.floor(Math.random() * stringSet.length)];
    }
    return token;
  }
  onSubmit() {
    this.registrationForm.value.token = this.randomString(14);
    this.userEntry.postUserDetails(this.registrationForm.value);
    const  value = this.registrationForm.value ;
    localStorage.setItem("token",value.token);
    this.userEntry.token = value.token;
    this.router.navigate(['/home']);
  }
}
