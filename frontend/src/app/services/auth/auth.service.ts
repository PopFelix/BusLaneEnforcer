import { HttpClient, HttpContext, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { TokenService } from '../token/token.service';
import { Router } from '@angular/router';
import { tap } from 'rxjs';
import { BYPASS_LOG } from 'src/app/interceptors/auth/auth.interceptor';



@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private tokenService: TokenService, private router: Router) { 
  }

  invalidCredentials = false;

  localLogin(username: string, password: string) {
    const body = new HttpParams()
      .set('username', username)
      .set('password', password);

    const headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');

    this.invalidCredentials = false;

    return this.http.post(`${environment.baseUrl}/login`, body, { headers, responseType: 'json' })
      .subscribe({
        next: (response: any) => {
          this.tokenService.saveAccessToken(response['accessToken']);
          this.tokenService.saveRefreshToken(response['refreshToken']);
          this.router.navigate(['/']);
        },
        error: (error) => {
          if (!(error.error instanceof ProgressEvent)) {
            this.invalidCredentials = true;
          }
        }
      });
  }



  login(code: string){
    const loginUrl = `${environment.baseUrl}/login?code=${code}`;
    return this.http.get(loginUrl).subscribe({
      next: (response: any) => {
        this.tokenService.saveAccessToken(response['accessToken']);
        this.tokenService.saveRefreshToken(response['refreshToken']);
        this.router.navigate(['/clients']);
      },
      error: (error) => {
        if (!(error.error instanceof ProgressEvent)) {
          this.invalidCredentials = true;
        }
      }
    });
  }

  getRefreshToken() {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json');

    return this.http.post(`${environment.baseUrl}/user/refresh`, this.tokenService.getRefreshToken(), { headers, context: new HttpContext().set(BYPASS_LOG, true), responseType: 'json' })
      .pipe(tap((token: any) => {
        this.tokenService.saveAccessToken(token['accessToken']);
        this.tokenService.saveRefreshToken(token['refreshToken']);
      }));
  }

}
