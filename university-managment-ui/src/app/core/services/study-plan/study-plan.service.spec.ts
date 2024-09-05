import { TestBed } from '@angular/core/testing';

import { StudyPlanService } from './study-plan.service';

describe('StudyPlanService', () => {
  let service: StudyPlanService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudyPlanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
