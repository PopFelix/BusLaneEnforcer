import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpContextToken,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, catchError, switchMap, throwError } from 'rxjs';
import { TokenService } from 'src/app/services/token/token.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import * as moment from 'moment'
import jwt_decode from 'jwt-decode';
import { error } from 'console';



export const BYPASS_LOG = new HttpContextToken(() => false);


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService, private authService: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const accessToken = this.tokenService.getAccessToken();
    const refreshToken = this.tokenService.getRefreshToken();
    const currentDate = moment(new Date((new Date()).getTime()))
    let accessTokenExpirationDate: moment.Moment;
    let refreshTokenExpirationDate: moment.Moment;

    if (refreshToken){
      refreshTokenExpirationDate = moment.unix(this.getDecodedToken(refreshToken as string).exp)
    }

    if (accessToken && !request.context.get(BYPASS_LOG)){
      accessTokenExpirationDate = moment.unix(this.getDecodedToken(accessToken as string).exp);
      request = this.addTokenHeader(request,accessToken as string);
    }
    return next.handle(request).pipe(catchError(error => {
      if (error instanceof HttpErrorResponse && error.status === 401 &&
        accessTokenExpirationDate.isBefore(currentDate) &&
        refreshTokenExpirationDate.isBefore(currentDate)){
          this.tokenService.signOut();
          return throwError(() => error);
        }
        else if (error instanceof HttpErrorResponse && error.status === 401 && !request.url.includes('/login')){
            return this.handleRefreshToken(request,next);
        }
        else{
            return throwError(() => error);
        }
    }));
  }

  private addTokenHeader(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  private handleRefreshToken(request: HttpRequest<any>, next: HttpHandler) {
    return this.authService.getRefreshToken().pipe(
      switchMap((token) => {
        return next.handle(this.addTokenHeader(request, token.accessToken));
      }));
  }

  private getDecodedToken(token: string): any {
    try {
      return jwt_decode(token);
    } catch(error) {
      return null;
    }
  }

}

