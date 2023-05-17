import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private TOKEN_KEY = 'accessToken';
  private REFRESHTOKEN_KEY = 'refreshToken';

  constructor(private router: Router) { }

  signOut(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

  public saveAccessToken(token: string): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  public getAccessToken(): string | null{
    return localStorage.getItem(this.TOKEN_KEY);
  }

  public saveRefreshToken(token: string): void {
    localStorage.removeItem(this.REFRESHTOKEN_KEY);
    localStorage.setItem(this.REFRESHTOKEN_KEY, token);
  }

  public getRefreshToken(): string | null {
    return localStorage.getItem(this.REFRESHTOKEN_KEY);
  }

}
