import { TestBed } from '@angular/core/testing';

import { TestTypeService } from './test-type.service';

describe('TestTypeService', () => {
  let service: TestTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TestTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
