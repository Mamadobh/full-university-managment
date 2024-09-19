import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecapStudyPlanComponent } from './recap-study-plan.component';

describe('RecapStudyPlanComponent', () => {
  let component: RecapStudyPlanComponent;
  let fixture: ComponentFixture<RecapStudyPlanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecapStudyPlanComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecapStudyPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
