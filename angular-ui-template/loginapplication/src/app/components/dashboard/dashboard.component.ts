import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['../home/home.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(public translate: TranslateService) {
    translate.addLangs(['English', 'Dutch', 'Russian']);
    translate.setDefaultLang('English');
  }
  buttonToggler: boolean;

  ngOnInit(): void {
  }

  onButtonClick() {
    this.buttonToggler = !this.buttonToggler;
  }
  switchLang(lang: string) {
    this.translate.use(lang);
  }

  logout(){
    localStorage.removeItem("UserName");
  }
}
