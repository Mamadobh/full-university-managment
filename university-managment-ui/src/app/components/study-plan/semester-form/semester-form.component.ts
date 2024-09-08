import {ChangeDetectionStrategy, Component, inject, OnInit} from '@angular/core';
import {
  DatePickerCustomHeaderComponent
} from "../../shared/date-picker-custom-header/date-picker-custom-header.component";
import {MatButton} from "@angular/material/button";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatFormField, MatSuffix} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {ModuleFormComponent} from "../module-form/module-form.component";
import {FormControl, ReactiveFormsModule} from "@angular/forms";
import * as _moment from 'moment';
import {default as _rollupMoment} from 'moment';
import {provideMomentDateAdapter} from "@angular/material-moment-adapter";
import {StudyPlanService} from "../../../core/services/study-plan/study-plan.service";

const moment = _rollupMoment || _moment;
export const MY_FORMATS = {
  parse: {
    dateInput: 'DD/MMM',
  },
  display: {
    dateInput: 'DD/MMM',
    monthYearLabel: 'MMM',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY'
  },
};

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
    ReactiveFormsModule
  ], providers: [provideMomentDateAdapter(MY_FORMATS)],

  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './semester-form.component.html',
  styleUrl: './semester-form.component.scss'
})
export class SemesterFormComponent implements OnInit {

  date = new FormControl(moment());
  date2 = new FormControl(moment());
  myHeader = DatePickerCustomHeaderComponent;
  studyPlanService = inject(StudyPlanService)

  constructor() {
    this.studyPlanService.studyPlanFrom = this.studyPlanService.createFrom("init")
    this.studyPlanService.studyPlanFrom?.valueChanges.subscribe((data) => {
      console.log(data)
    })
  }

  ngOnInit() {

  }

  log() {
    console.log("this.getall modules ", this.studyPlanService.getAllModules().value)
  }
}
