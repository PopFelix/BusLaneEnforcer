import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, map, tap } from 'rxjs';
import { AcceptedVehicle } from 'src/app/components/accepted-vehicles/accepted-vehicle';
import { UnauthorizedLog } from 'src/app/components/unauthorized-vehicles/unauthorized-log';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {
  vehicleUrl = environment.baseUrl+'/vehicle/';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(protected http: HttpClient) { }

  private _refreshRequiered = new Subject<void>();

  get requiredRefresh(){
    return this._refreshRequiered;
  }
  refresh(){
    this.getAcceptedVehicles()
  }

  getAcceptedVehicles(): Observable<AcceptedVehicle[]>{
    return this.http.get<any>(this.vehicleUrl+'accepted');
  }

  addAcceptedVehicle(vehicle: AcceptedVehicle): Observable<AcceptedVehicle> {
    return this.http.post<AcceptedVehicle>(this.vehicleUrl+'accepted', vehicle).pipe(
      tap(() => {
        this.requiredRefresh.next();
      })
    )
  }

  removeAcceptedVehicle(vehicle: AcceptedVehicle): Observable<Object> {
    const url = `${this.vehicleUrl}accepted/${vehicle.externalId}`;
    console.log(url);
    return this.http.delete(url);
  }


  updateAcceptedVehicle(externalId: string,vehicle: AcceptedVehicle): Observable<AcceptedVehicle> {
    const url = `${this.vehicleUrl}accepted/${externalId}`;
    vehicle.externalId = externalId;
    console.log(vehicle);
    return this.http.put<AcceptedVehicle>(url, vehicle).pipe(
      tap(() => {
        this.requiredRefresh.next();
      })
    );
  }

  getUnauthorizedLogs(): Observable<UnauthorizedLog[]>{
    return this.http.get<any>(this.vehicleUrl+'unauthorized');
  }

}
