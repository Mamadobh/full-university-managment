import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatePickerCustomHeaderComponent } from './date-picker-custom-header.component';

describe('DatePickerCustomHeaderComponent', () => {
  let component: DatePickerCustomHeaderComponent;
  let fixture: ComponentFixture<DatePickerCustomHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DatePickerCustomHeaderComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DatePickerCustomHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
