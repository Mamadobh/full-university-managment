import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyPlanHeaderComponent } from './study-plan-header.component';

describe('StudyPlanHeaderComponent', () => {
  let component: StudyPlanHeaderComponent;
  let fixture: ComponentFixture<StudyPlanHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudyPlanHeaderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudyPlanHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
