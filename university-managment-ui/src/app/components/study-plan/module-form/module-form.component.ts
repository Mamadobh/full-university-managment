import {Component, EventEmitter, inject, Input, Output} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {User} from "../../../pages/study-plan-pages/study-plan-create/study-plan-create.component";
import {map, Observable, startWith} from "rxjs";
import {AsyncPipe} from "@angular/common";
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from "@angular/material/autocomplete";
import {MatFormField} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatInput} from "@angular/material/input";
import {SubjectFormComponent} from "../subject-form/subject-form.component";
import {MatButton} from "@angular/material/button";
import {StudyPlanService} from "../../../core/services/study-plan/study-plan.service";
import {DepartmentService} from "../../../core/services/department/department.service";
import {DepartmentResponseModel} from "../../../core/services/department/model/DepartmentResponse.model";
import {AutoCompleteFormComponent} from "../../shared/auto-complete-form/auto-complete-form.component";


@Component({
  selector: 'app-module-form',
  standalone: true,
  imports: [AsyncPipe,
    MatAutocomplete,
    MatAutocompleteTrigger,
    MatFormField,
    MatIcon,
    MatInput,
    MatOption,
    ReactiveFormsModule, SubjectFormComponent, MatButton, AutoCompleteFormComponent],
  templateUrl: './module-form.component.html',
  styleUrl: './module-form.component.scss',


})
export class ModuleFormComponent {
  myControl = new FormControl<string | User>('');
  options: User[] = [{name: 'Mary'}, {name: 'Shelley'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}];
  filteredOptions!: Observable<User[]>;
  studyPlanService = inject(StudyPlanService)
  departmentService = inject(DepartmentService)
  _formModule!: FormGroup;


  @Input()
  set formModule(value: AbstractControl) {
    this._formModule = value as FormGroup;
  }

  @Input() moduleIndex!: number;

  constructor() {
  }


  dataOptions: DepartmentResponseModel[] = []

  getfield(name: string): FormControl {
    return this._formModule.get(name) as FormControl
  }

  ngOnInit() {
    this.departmentService.allDepartmentReponse.subscribe((data) => {
      this.dataOptions = data.content
    })
    this.departmentService.findDepartment()

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

  @Output()
  a = new EventEmitter<string>()

  removeModule() {
    this.studyPlanService.removeModule(this.moduleIndex);
  }
}
