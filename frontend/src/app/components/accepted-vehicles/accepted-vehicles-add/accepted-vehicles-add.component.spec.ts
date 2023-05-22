import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptedVehiclesAddComponent } from './accepted-vehicles-add.component';

describe('AcceptedVehiclesAddComponent', () => {
  let component: AcceptedVehiclesAddComponent;
  let fixture: ComponentFixture<AcceptedVehiclesAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcceptedVehiclesAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptedVehiclesAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
