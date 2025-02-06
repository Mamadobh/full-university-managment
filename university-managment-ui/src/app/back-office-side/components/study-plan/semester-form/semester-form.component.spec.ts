import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SemesterFormComponent } from './semester-form.component';

describe('SemesterFormComponent', () => {
  let component: SemesterFormComponent;
  let fixture: ComponentFixture<SemesterFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SemesterFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SemesterFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
