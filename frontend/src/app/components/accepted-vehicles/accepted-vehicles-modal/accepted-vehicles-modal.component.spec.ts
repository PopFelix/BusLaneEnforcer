import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptedVehiclesModalComponent } from './accepted-vehicles-modal.component';

describe('AcceptedVehiclesModalComponent', () => {
  let component: AcceptedVehiclesModalComponent;
  let fixture: ComponentFixture<AcceptedVehiclesModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcceptedVehiclesModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptedVehiclesModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
