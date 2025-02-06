import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyPlanDisplayComponent } from './study-plan-display.component';

describe('StudyPlanDisplayComponent', () => {
  let component: StudyPlanDisplayComponent;
  let fixture: ComponentFixture<StudyPlanDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudyPlanDisplayComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudyPlanDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
