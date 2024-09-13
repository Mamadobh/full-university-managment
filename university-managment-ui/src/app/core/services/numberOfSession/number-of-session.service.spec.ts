import { TestBed } from '@angular/core/testing';

import { NumberOfSessionService } from './number-of-session.service';

describe('NumberOfSessionService', () => {
  let service: NumberOfSessionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NumberOfSessionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
