import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DefaultComponent } from './components/layouts/default/default.component';
import { AcceptedVehiclesComponent } from './components/accepted-vehicles/accepted-vehicles.component';
import { AuthGuard } from './guards/auth/auth.guard';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './interceptors/auth/auth.interceptor';
import { AuthService } from './services/auth/auth.service';
import { TokenService } from './services/token/token.service';
import { UnauthorizedVehiclesComponent } from './components/unauthorized-vehicles/unauthorized-vehicles.component';
import { UploadComponent } from './components/upload/upload.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: DefaultComponent,
  canActivate: [AuthGuard],
  children: [{
    path:'accepted-vehicles',
    component:AcceptedVehiclesComponent
  },
{
  path:'unauthorized-vehicles',
  component: UnauthorizedVehiclesComponent
},
{
  path:'upload',
  component: UploadComponent
}]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},AuthService,TokenService,AuthGuard],
  exports: [RouterModule]
})
export class AppRoutingModule { }
