import {Component, inject, Input} from '@angular/core';
import {AsyncPipe} from "@angular/common";
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from "@angular/material/autocomplete";
import {MatButton} from "@angular/material/button";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {AbstractControl, FormArray, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {SubjectTypeFormComponent} from "../subject-type-form/subject-type-form.component";
import {TestFormComponent} from "../test-form/test-form.component";
import {User} from "../../../pages/study-plan-pages/study-plan-create/study-plan-create.component";
import {map, Observable, startWith} from "rxjs";
import {StudyPlanService} from "../../../core/services/study-plan/study-plan.service";

@Component({
  selector: 'app-subject-form',
  standalone: true,
  imports: [
    AsyncPipe,
    MatAutocomplete,
    MatAutocompleteTrigger,
    MatButton,
    MatFormField,
    MatInput,
    MatOption,
    ReactiveFormsModule,
    SubjectTypeFormComponent,
    TestFormComponent
  ],
  templateUrl: './subject-form.component.html',
  styleUrl: './subject-form.component.scss'
})
export class SubjectFormComponent {
  myControl = new FormControl<string | User>('');
  options: User[] = [{name: 'Mary'}, {name: 'Shelley'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}];
  filteredOptions!: Observable<User[]>;
  _formSubject!: FormGroup;
  studyPlanService = inject(StudyPlanService)

  constructor() {
    this.studyPlanService.studyPlanFrom.valueChanges.subscribe((data) => {
      console.log("data ", data)
    })
  }

  @Input()
  set formSubject(value: AbstractControl) {
    this._formSubject = value as FormGroup;
  }

  @Input() subjectIndex!: number;
  @Input() moduleIndex!: number;


  get tests(): FormArray {
    return this._formSubject.get('tests') as FormArray;
  }

  removeSubject() {
    this.studyPlanService.removeSubject(this.moduleIndex, this.subjectIndex)
  }

  get subjectTypes(): FormArray {
    return this._formSubject.get('subjectTypes') as FormArray;
  }

  ngOnInit() {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        const name = typeof value === 'string' ? value : value?.name;
        return name ? this._filter(name as string) : this.options.slice();
      }),
    );
  }

  getDisplayValue(): string {
    const rawValue = this.myControl.getRawValue();
    if (rawValue && typeof rawValue === 'object' && 'name' in rawValue) {
      return (rawValue as User).name;
    }
    return rawValue as string;
  }

  displayFn(user: User): string {
    return user && user.name ? user.name : '';
  }

  private _filter(name: string): User[] {
    const filterValue = name.toLowerCase();

    return this.options.filter(option => option.name.toLowerCase().includes(filterValue));
  }

  /* end start autocoomlute module type*/

  prevent($event: Event) {

    $event.preventDefault()
    $event.stopPropagation()
  }

  addTest($event: number) {
    this.studyPlanService.addTest(this.moduleIndex, this.subjectIndex)
  }

  removeTest(testIndex: number) {
    this.studyPlanService.removeTest(this.moduleIndex, this.subjectIndex, testIndex)

  }

  addSubjectType($event: number) {
    this.studyPlanService.addSubjectType(this.moduleIndex, this.subjectIndex)
  }

  removeSubjectType(subjectTypeIndex: number) {
    this.studyPlanService.removeSubjectType(this.moduleIndex, this.subjectIndex, subjectTypeIndex)

  }
}
