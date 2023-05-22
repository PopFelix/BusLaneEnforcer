import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptedVehiclesComponent } from './accepted-vehicles.component';

describe('AcceptedVehiclesComponent', () => {
  let component: AcceptedVehiclesComponent;
  let fixture: ComponentFixture<AcceptedVehiclesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcceptedVehiclesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptedVehiclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
