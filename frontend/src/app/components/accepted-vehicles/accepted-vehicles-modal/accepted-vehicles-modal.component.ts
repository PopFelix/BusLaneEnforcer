import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { VehicleService } from 'src/app/services/vehicles/vehicle.service';

@Component({
  selector: 'app-accepted-vehicles-modal',
  templateUrl: './accepted-vehicles-modal.component.html',
  styleUrls: ['./accepted-vehicles-modal.component.css']
})
export class AcceptedVehiclesModalComponent implements OnInit {
  activities = ['Public Transport', 'Hospital Services', 'Law Enforcement','Taxi','Carpool','Bike','Other'];
  form = new FormGroup({
    licensePlateNumber: new FormControl("",Validators.required),
    carType: new FormControl(""),
    make: new FormControl(""),
    model: new FormControl(""),
    activityType: new FormControl("Other")
  })
  constructor(private vehicleService: VehicleService, private dialog: MatDialogRef<AcceptedVehiclesModalComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }
  sentData: any;
  response: any;
  editData: any;
  dialogText = 'Add accepted vehicle';
  ngOnInit(): void {
    if (this.data!=null){
      this.loadEditData();
      this.dialogText = 'Update accepted vehicle';
    }
  }

  

  addAcceptedVehicle(){
      if (this.form.valid){
        if (this.data!=null){
          console.log(this.data);
          this.vehicleService.updateAcceptedVehicle(this.data.externalId,this.form.value)
          .subscribe(_ => {
            return this.dialog.close();
          } )
        }
        else{
          this.vehicleService.addAcceptedVehicle(this.form.value)
          .subscribe(_ => {
            return this.dialog.close();
          } )
        }
       
      }
  }

  loadEditData(){
    this.editData=this.data;
    this.form.setValue({ licensePlateNumber:this.editData.licensePlateNumber,
    carType: this.editData.carType,
    make: this.editData.make,
    model: this.editData.model,
    activityType: this.editData.activityType})
  }

}
