import { Component, OnInit} from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public translate: TranslateService, private modalService: NgbModal) {
    translate.addLangs(['English', 'Dutch', 'Russian']);
    translate.setDefaultLang('English');
  }
  buttonToggler: boolean =true;


  ngOnInit(): void {
  }


  onButtonClick() {
    this.buttonToggler = !this.buttonToggler;
  }
  switchLang(lang: string) {
    this.translate.use(lang);
  }



  logout(){
    //localStorage.removeItem("UserName");
  }
  title = 'appBootstrap';

  closeResult: string;



  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', size :'sm', windowClass: 'user-profile'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }
}
