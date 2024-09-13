import { TestBed } from '@angular/core/testing';

import { CoefficientService } from './coefficient.service';

describe('CoefficientService', () => {
  let service: CoefficientService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CoefficientService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
