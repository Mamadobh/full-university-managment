import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthBackOfficeComponent } from './auth-back-office.component';

describe('AuthBackOfficeComponent', () => {
  let component: AuthBackOfficeComponent;
  let fixture: ComponentFixture<AuthBackOfficeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuthBackOfficeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuthBackOfficeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
