import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudyPlanCreateComponent } from './study-plan-create.component';

describe('StudyPlanCreateComponent', () => {
  let component: StudyPlanCreateComponent;
  let fixture: ComponentFixture<StudyPlanCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudyPlanCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudyPlanCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
