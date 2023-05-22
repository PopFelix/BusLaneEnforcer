import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { TokenService } from 'src/app/services/token/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  username!: string;
  password!: string;

  constructor(private formBuilder: FormBuilder,private router: Router, private route: ActivatedRoute, private authService: AuthService, private tokenService: TokenService) { }

  ngOnInit() {
    document.body.className = "selector";
    if(this.tokenService.getAccessToken()) {
      this.router.navigate(['/'])
    }
  }
  onLogin() {
    this.authService.login(this.username, this.password);
  }



ngOnDestroy(){
    document.body.className="";
  }

}
