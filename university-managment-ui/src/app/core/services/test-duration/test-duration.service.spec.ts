import { TestBed } from '@angular/core/testing';

import { TestDurationService } from './test-duration.service';

describe('TestDurationService', () => {
  let service: TestDurationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TestDurationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
