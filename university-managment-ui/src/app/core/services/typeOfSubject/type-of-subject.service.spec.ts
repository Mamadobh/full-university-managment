import { TestBed } from '@angular/core/testing';

import { TypeOfSubjectService } from './type-of-subject.service';

describe('TypeOfSubjectService', () => {
  let service: TypeOfSubjectService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TypeOfSubjectService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
