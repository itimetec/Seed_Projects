import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call the function when button is cliked',async(() => {
    //arrange
    let button = fixture.debugElement.nativeElement.querySelector('button');
    spyOn(component, 'login');
    //act
    button.click();
    //assert
    fixture.whenStable().then(() => {
      expect(component.login).toHaveBeenCalled();
    });
  }));
});
