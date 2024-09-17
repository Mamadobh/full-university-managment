import {Component, inject, Input, OnInit, signal} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {MatError, MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {SubjectFormComponent} from "../subject-form/subject-form.component";
import {MatButton} from "@angular/material/button";
import {StudyPlanService} from "../../../core/services/study-plan/study-plan.service";
import {AutoCompleteFormComponent} from "../../shared/auto-complete-form/auto-complete-form.component";
import {ModuleTypeService} from "../../../core/services/moduleType/module-type.service";
import {ModuleTypeResponse} from "../../../core/services/moduleType/model/module-type.model";
import {JsonPipe} from "@angular/common";


@Component({
  selector: 'app-module-form',
  standalone: true,
  imports: [
    MatFormField,
    MatInput,
    ReactiveFormsModule,
    SubjectFormComponent,
    MatButton,
    AutoCompleteFormComponent,
    MatError,
    JsonPipe
  ],
  templateUrl: './module-form.component.html',
  styleUrl: './module-form.component.scss',


})
export class ModuleFormComponent implements OnInit {
  studyPlanService = inject(StudyPlanService)
  _formModule!: FormGroup;
  moduletypeService = inject(ModuleTypeService)
  dataOptionsModuleType = signal<ModuleTypeResponse[]>([])
  errorMsg: string = "field is required !!"
  duplicationError = "module is duplicated !!"

  @Input()
  set formModule(value: AbstractControl) {
    this._formModule = value as FormGroup;
  }

  @Input() moduleIndex!: number;
  @Input() semesterIndex!: number;

  getfield(name: string): FormControl {
    return this._formModule.get(name) as FormControl
  }

  ngOnInit() {
    this.moduletypeService.allModuleTypeReponse.subscribe((data) => {
      this.dataOptionsModuleType.set(data.content)
    })

    this.moduletypeService.findAllModuleTypes()
  }

  displayFn(moduleType: ModuleTypeResponse): string {
    return moduleType && moduleType.type ? moduleType.type : '';
  }

  removeModule() {
    this.studyPlanService.removeModule(this.semesterIndex, this.moduleIndex);
  }

  createItem(item: string) {
    this.moduletypeService.addModuleType(
      {type: item}
    ).subscribe({
      next: (res) => {
        this.moduletypeService.findAllModuleTypes()
      }
    })
  }
}
