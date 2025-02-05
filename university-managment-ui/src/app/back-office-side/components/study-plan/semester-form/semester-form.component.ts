import {ChangeDetectionStrategy, Component, computed, inject, Input, OnInit} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatError, MatFormField, MatSuffix} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {ModuleFormComponent} from "../module-form/module-form.component";
import {AbstractControl, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {JsonPipe} from "@angular/common";

import * as _moment from 'moment';
import {default as _rollupMoment} from 'moment';
import {provideMomentDateAdapter} from "@angular/material-moment-adapter";
import {StudyPlanService} from "../../../../core/services/study-plan/study-plan.service";

export const MY_FORMATS = {
  parse: {
    dateInput: 'YYYY/MM/DD',
  },
  display: {
    dateInput: 'YYYY/MM/DD',
    monthYearLabel: 'MMMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY'
  },
};
const moment = _rollupMoment || _moment;

@Component({
  selector: 'app-semester-form',
  standalone: true,
  imports: [
    MatButton,
    MatDatepicker,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatFormField,
    MatInput,
    MatSuffix,
    ModuleFormComponent,
    ReactiveFormsModule,
    MatError,
    JsonPipe
  ], providers: [provideMomentDateAdapter(MY_FORMATS)],

  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './semester-form.component.html',
  styleUrl: './semester-form.component.scss'
})
export class SemesterFormComponent implements OnInit {

  studyPlanService = inject(StudyPlanService)
  _formSemester!: FormGroup
  errorMsg: string = "field is required !!"
  duplicationError = "semester is duplicated !!"
  isformSubmited = computed(() => this.studyPlanService.isFormSubmited())

  @Input()
  set formSemester(value: AbstractControl) {
    this._formSemester = value as FormGroup;
  }

  @Input() semesterIndex!: number;

  getfield(name: string): FormControl {
    return this._formSemester.get(name) as FormControl
  }

  ngOnInit() {
  }

}
