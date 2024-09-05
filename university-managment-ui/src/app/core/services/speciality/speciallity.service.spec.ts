import { TestBed } from '@angular/core/testing';

import { SpeciallityService } from './speciallity.service';

describe('SpeciallityService', () => {
  let service: SpeciallityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SpeciallityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
