import { Component, OnInit, ViewChild } from '@angular/core';
import { AcceptedVehicle } from '../accepted-vehicle';
import { MatTableDataSource } from '@angular/material/table';
import { VehicleService } from 'src/app/services/vehicles/vehicle.service';
import { Router } from '@angular/router';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AcceptedVehiclesModalComponent } from '../accepted-vehicles-modal/accepted-vehicles-modal.component';
import * as alertify from 'alertifyjs'


@Component({
  selector: 'app-accepted-vehicles-show',
  templateUrl: './accepted-vehicles-show.component.html',
  styleUrls: ['./accepted-vehicles-show.component.css']
})
export class AcceptedVehiclesShowComponent implements OnInit {


  errorMsg!: string;
  vehicles!: AcceptedVehicle[];
  selectedVehicle!: AcceptedVehicle;
  displayedColumns: string[] = ['licensePlateNumber','carType','make','model','activityType','action'];
  dataSource!: MatTableDataSource<AcceptedVehicle>;
  constructor(private vehicleService: VehicleService, private router: Router, private dialog:MatDialog) { }
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  @ViewChild(MatSort)
  sort!: MatSort;

  ngOnInit(): void {
    this.getAcceptedVehicles();
    this.vehicleService.requiredRefresh.subscribe(r => {
      this.getAcceptedVehicles();
    })
  }
  private getAcceptedVehicles() {
    this.vehicleService.getAcceptedVehicles()
    .subscribe({
      next:(vehicles) => {
        this.vehicles = vehicles;
        this.dataSource = new MatTableDataSource<AcceptedVehicle>(this.vehicles);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;

      },
      error: (error) =>   {this.errorMsg = <any> error;}

      }
    )
  }

  selectVehicle(vehicle: AcceptedVehicle): void {
    this.selectedVehicle = vehicle;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  goToAdd(){
      this.dialog.open(AcceptedVehiclesModalComponent, {
      })
  }
  goToUpdate(vehicle: AcceptedVehicle) {
    this.dialog.open(AcceptedVehiclesModalComponent, {
      data: vehicle
    })
    }

  openDeleteDialog(vehicle: AcceptedVehicle){
    const dialogRef = this.dialog.open(DeleteDialog);
    dialogRef.afterClosed().subscribe(result => {
    });
  }
  goToDelete(vehicle: AcceptedVehicle) {
    alertify.confirm("Remove Employee","Do you want to remove?",()=>{
      this.vehicleService.removeAcceptedVehicle(vehicle).subscribe(result => {
        this.getAcceptedVehicles();
        alertify.success("Removed successfully.")
      });
  
    },function(){
  
    })
  
  }
}


@Component({
  selector: 'delete-dialog',
  templateUrl: 'delete-dialog.html',
})
export class DeleteDialog {
  constructor(public dialogRef: MatDialogRef<DeleteDialog>) {}
}