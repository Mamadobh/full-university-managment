import { TestBed } from '@angular/core/testing';

import { ModuleTypeService } from './module-type.service';

describe('ModuleTypeService', () => {
  let service: ModuleTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ModuleTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
