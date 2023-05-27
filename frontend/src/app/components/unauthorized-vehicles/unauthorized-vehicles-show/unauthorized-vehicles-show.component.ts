import { Component, OnInit, ViewChild } from '@angular/core';
import { UnauthorizedLog } from '../unauthorized-log';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { VehicleService } from 'src/app/services/vehicles/vehicle.service';
import { AcceptedVehicle } from '../../accepted-vehicles/accepted-vehicle';
import { FormControl, FormGroup } from '@angular/forms';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-unauthorized-vehicles-show',
  templateUrl: './unauthorized-vehicles-show.component.html',
  styleUrls: ['./unauthorized-vehicles-show.component.css']
})
export class UnauthorizedVehiclesShowComponent implements OnInit {

  errorMsg!: string;
  logs!: UnauthorizedLog[];
  selectedLog!: UnauthorizedLog | null;
  displayedColumns: string[] = ['licensePlateNumber','logDate','actions'];
  displayedExpandedColumns: string[] = ['expandedDetail'];
  dataSource!: MatTableDataSource<UnauthorizedLog>;
  constructor(private vehicleService: VehicleService, private router: Router, private dialog:MatDialog) { }
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  videoUrl = environment.baseUrl+'/video/';

  @ViewChild(MatSort)
  sort!: MatSort;
  range = new FormGroup({
    start: new FormControl(null),
    end:  new FormControl(null),
  });

  ngOnInit(): void {
    this.getAcceptedVehicles();
    this.vehicleService.requiredRefresh.subscribe(r => {
      this.getAcceptedVehicles();
    })
  }
  private getAcceptedVehicles() {
    this.vehicleService.getUnauthorizedLogs()
    .subscribe({
      next:(logs) => {
        this.logs = logs;
        this.dataSource = new MatTableDataSource<UnauthorizedLog>(this.logs);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;

      },
      error: (error) =>   {this.errorMsg = <any> error;}

      }
    )
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  toggleExpansion(element: UnauthorizedLog): void {
    this.selectedLog = this.selectedLog === element ? null : element;

  }

}
