import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptedVehiclesUpdateComponent } from './accepted-vehicles-update.component';

describe('AcceptedVehiclesUpdateComponent', () => {
  let component: AcceptedVehiclesUpdateComponent;
  let fixture: ComponentFixture<AcceptedVehiclesUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcceptedVehiclesUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptedVehiclesUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
