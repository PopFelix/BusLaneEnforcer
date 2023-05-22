import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptedVehiclesShowComponent } from './accepted-vehicles-show.component';

describe('AcceptedVehiclesShowComponent', () => {
  let component: AcceptedVehiclesShowComponent;
  let fixture: ComponentFixture<AcceptedVehiclesShowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcceptedVehiclesShowComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptedVehiclesShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
