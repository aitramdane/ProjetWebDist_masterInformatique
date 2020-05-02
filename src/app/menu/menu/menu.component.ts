import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  title = '';

  constructor(private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
  }

  gotoRoomPage(){
    this.router.navigateByUrl('/room');
  }

  gotoClientPage(){
    this.router.navigateByUrl('/client');
  }

  gotoReservationPage(){
    this.router.navigateByUrl('/reservation');
  }

  getUrl() {
    return "url('./assets/background2.jpg')";
  }

}






