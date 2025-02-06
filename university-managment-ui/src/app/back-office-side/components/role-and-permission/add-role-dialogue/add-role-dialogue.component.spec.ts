import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRoleDialogueComponent } from './add-role-dialogue.component';

describe('AddRoleDialogueComponent', () => {
  let component: AddRoleDialogueComponent;
  let fixture: ComponentFixture<AddRoleDialogueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddRoleDialogueComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddRoleDialogueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
