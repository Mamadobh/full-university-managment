import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoleAndPermissionsComponent } from './role-and-permissions.component';

describe('RoleAndPermissionsComponent', () => {
  let component: RoleAndPermissionsComponent;
  let fixture: ComponentFixture<RoleAndPermissionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoleAndPermissionsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoleAndPermissionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
