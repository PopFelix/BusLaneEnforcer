import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import {MatCardModule} from '@angular/material/card';
import {MatTabsModule} from '@angular/material/tabs';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav';
import { DefaultComponent } from './components/layouts/default/default.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { SidebarComponent } from './components/shared/sidebar/sidebar.component';
import { AcceptedVehiclesComponent } from './components/accepted-vehicles/accepted-vehicles.component'
import {MatDividerModule} from '@angular/material/divider'
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatListModule} from '@angular/material/list';
import { AcceptedVehiclesShowComponent } from './components/accepted-vehicles/accepted-vehicles-show/accepted-vehicles-show.component';
import { AcceptedVehiclesAddComponent } from './components/accepted-vehicles/accepted-vehicles-add/accepted-vehicles-add.component';
import { AcceptedVehiclesUpdateComponent } from './components/accepted-vehicles/accepted-vehicles-update/accepted-vehicles-update.component';
import {MatTableModule} from '@angular/material/table';
import { MatFormFieldModule } from "@angular/material/form-field";
import {MatPaginatorModule} from "@angular/material/paginator";
import { MatSortModule } from '@angular/material/sort';
import { AcceptedVehiclesModalComponent } from './components/accepted-vehicles/accepted-vehicles-modal/accepted-vehicles-modal.component';
import {MatDialogModule} from '@angular/material/dialog'
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatSelectModule } from '@angular/material/select';
import { UnauthorizedVehiclesComponent } from './components/unauthorized-vehicles/unauthorized-vehicles.component';
import { UnauthorizedVehiclesShowComponent } from './components/unauthorized-vehicles/unauthorized-vehicles-show/unauthorized-vehicles-show.component';
import { MatNativeDateModule } from '@angular/material/core';
import { SecurePipe } from './pipes/secure.pipe';
import { UploadComponent } from './components/upload/upload.component';
import { DragDropDirective } from './directives/drag-drop.directive';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DefaultComponent,
    HeaderComponent,
    FooterComponent,
    SidebarComponent,
    AcceptedVehiclesComponent,
    AcceptedVehiclesShowComponent,
    AcceptedVehiclesAddComponent,
    AcceptedVehiclesUpdateComponent,
    AcceptedVehiclesModalComponent,
    UnauthorizedVehiclesComponent,
    UnauthorizedVehiclesShowComponent,
    SecurePipe,
    UploadComponent,
    DragDropDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatTabsModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    FormsModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatDividerModule,
    FlexLayoutModule,
    MatListModule,
    MatTableModule,
    MatFormFieldModule,
    MatPaginatorModule,
    MatSortModule,
    MatDialogModule,
    MatDatepickerModule,
    MatSelectModule,
    MatNativeDateModule
  ],
  providers: [MatDatepickerModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
