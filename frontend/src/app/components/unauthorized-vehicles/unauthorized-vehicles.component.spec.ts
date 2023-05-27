import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauthorizedVehiclesComponent } from './unauthorized-vehicles.component';

describe('UnauthorizedVehiclesComponent', () => {
  let component: UnauthorizedVehiclesComponent;
  let fixture: ComponentFixture<UnauthorizedVehiclesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnauthorizedVehiclesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnauthorizedVehiclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
