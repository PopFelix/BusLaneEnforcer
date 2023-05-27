import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UnauthorizedVehiclesShowComponent } from './unauthorized-vehicles-show.component';

describe('UnauthorizedVehiclesShowComponent', () => {
  let component: UnauthorizedVehiclesShowComponent;
  let fixture: ComponentFixture<UnauthorizedVehiclesShowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UnauthorizedVehiclesShowComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UnauthorizedVehiclesShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
