import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';
import { TokenService } from 'src/app/services/token/token.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @Output() toggleSideBarEvent: EventEmitter<any> = new EventEmitter();

  constructor(private tokenService: TokenService) { }

  ngOnInit(): void {
  }

  toggleSideBar(){
    this.toggleSideBarEvent.emit();
  }

  signOut(){
    this.tokenService.signOut();
  }

}
