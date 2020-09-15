import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupComponent } from './signup.component';

describe('SignupComponent', () => {
  let component: SignupComponent;
  let fixture: ComponentFixture<SignupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('form invalid when empty', () => {
    expect(component.registrationForm.valid).toBeFalsy();
  });
  it('name field validity', () => {
    let userName = component.registrationForm.controls['userName'];
    expect(userName.valid).toBeFalsy();

    let errors = {};
    userName.setValue("");
    errors = userName.errors || {};
    expect(errors['required']).toBeTruthy();
    expect(userName.hasError('minlength', ['minlength'])).toEqual(false);
  });
  it('email field validity', () => {
    let email = component.registrationForm.controls['email'];
    expect(email.valid).toBeFalsy();

    let errors = {};
    email.setValue("");
    errors = email.errors || {};
    expect(errors['required']).toBeTruthy();
  });
  it('phoneNumber field validity', () => {
    let phoneNumber = component.registrationForm.controls['phoneNumber'];
    expect(phoneNumber.valid).toBeFalsy();

    let errors = {};
    phoneNumber.setValue("");
    errors = phoneNumber.errors || {};
    expect(errors['required']).toBeTruthy();
    expect(phoneNumber.hasError('pattern', ['pattern'])).toEqual(false);
  });
  it('password field validity', () => {
    let password = component.registrationForm.controls['password'];
    expect(password.valid).toBeFalsy();

    let errors = {};
    password.setValue("");
    errors = password.errors || {};
    expect(errors['required']).toBeTruthy();
    expect(password.hasError('minlength', ['minlength'])).toEqual(false);
  });
  it('termsAndConditions field validity', () => {
    let termsAndConditions = component.registrationForm.controls['termsAndConditions'];
    expect(termsAndConditions.valid).toBeFalsy();

    let errors = {};
    termsAndConditions.setValue("");
    errors = termsAndConditions.errors || {};
    expect(errors['required']).toBeTruthy();
  });

});
