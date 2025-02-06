import {Component, inject, Input, OnInit, signal} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {MatError, MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {AbstractControl, FormArray, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {SubjectTypeFormComponent} from "../subject-type-form/subject-type-form.component";
import {TestFormComponent} from "../test-form/test-form.component";
import {AutoCompleteFormComponent} from "../../shared/auto-complete-form/auto-complete-form.component";
import {JsonPipe} from "@angular/common";
import {StudyPlanService} from "../../../../core/services/study-plan/study-plan.service";
import {CoefficientResponse} from "../../../../core/services/coefficient/model/coefficient.model";
import {CoefficientService} from "../../../../core/services/coefficient/coefficient.service";
import {TestTypeResponse} from "../../../../core/services/test-type/model/test-type.model";
import {TestTypeService} from "../../../../core/services/test-type/test-type.service";


@Component({
  selector: 'app-subject-form',
  standalone: true,
  imports: [
    MatButton,
    MatFormField,
    MatInput,
    ReactiveFormsModule,
    SubjectTypeFormComponent,
    TestFormComponent,
    AutoCompleteFormComponent,
    MatError,
    JsonPipe
  ],
  templateUrl: './subject-form.component.html',
  styleUrl: './subject-form.component.scss'
})
export class SubjectFormComponent implements OnInit {
  _formSubject!: FormGroup;
  studyPlanService = inject(StudyPlanService)
  coefficientService = inject(CoefficientService)
  dataOptionsCoefficient = signal<CoefficientResponse[]>([])
  errorMsg: string = "field is required !!"
  duplicationError = "subject is duplicated !!"
  @Input() subjectIndex!: number;
  @Input() moduleIndex!: number;
  @Input() semesterIndex!: number;
  testTypeService = inject(TestTypeService)
  dataOptionsTestType = signal<TestTypeResponse[]>([])


  @Input()
  set formSubject(value: AbstractControl) {
    this._formSubject = value as FormGroup;
  }

  constructor() {
    this.studyPlanService.studyPlanFrom.valueChanges.subscribe((data) => {
      console.log("data ", data)
    })
  }


  ngOnInit() {
    this.testTypeService.allTestTypeReponse.subscribe((data) => {
      this.dataOptionsTestType.set(data.content)
    })
    this.coefficientService.allCoefficientReponse.subscribe((data) => {
      this.dataOptionsCoefficient.set(data.content)
    })

    this.coefficientService.findAllCoefficient()
  }

  get tests(): FormArray {
    return this._formSubject.get('tests') as FormArray;
  }

  removeSubject() {
    this.studyPlanService.removeSubject(this.semesterIndex, this.moduleIndex, this.subjectIndex)
  }

  get subjectTypes(): FormArray {
    return this._formSubject.get('subjectTypes') as FormArray;
  }

  getfield(name: string): FormControl {
    return this._formSubject.get(name) as FormControl
  }

  addTest($event: number) {
    this.studyPlanService.addTest(this.semesterIndex, this.moduleIndex, this.subjectIndex)
  }

  removeTest(testIndex: number) {
    this.studyPlanService.removeTest(this.semesterIndex, this.moduleIndex, this.subjectIndex, testIndex)

  }

  addSubjectType($event: number) {
    this.studyPlanService.addSubjectType(this.semesterIndex, this.moduleIndex, this.subjectIndex)
  }

  removeSubjectType(subjectTypeIndex: number) {
    this.studyPlanService.removeSubjectType(this.semesterIndex, this.moduleIndex, this.subjectIndex, subjectTypeIndex)

  }

  displayFn(coefficient: CoefficientResponse): string {
    return coefficient && coefficient.coefficient ? coefficient.coefficient.toString() : '';
  }

  createItem(item: string) {
    this.coefficientService.create(
      {coefficient: +item}
    ).subscribe({
      next: (res) => {
        this.coefficientService.findAllCoefficient()
      }
    })
  }

  findCoefficientById(id: number): number {
    return this.dataOptionsCoefficient().find((el) => el.id === id)?.coefficient as number;
  }

  findTestTypeById(id: number): string {
    return this.dataOptionsTestType().find((el) => el.id === id)?.testType as string;
  }


  getSubjectAvg(): string {
    let avg = "("
    let total = 0
    this.getfield("tests").value.forEach((el: any, index: number) => {
      if (el?.testTypeId && el?.coefficientId) {
        avg=index>0? avg+"+":avg
        total++
        const coef = this.findCoefficientById(el?.coefficientId) > 1 ? this.findCoefficientById(el?.coefficientId).toString()+"*" : ""
        avg = avg + coef  + this.findTestTypeById(el?.testTypeId)
      }
    })
    avg += ")"
    if (avg.length == 2) {
      return ""
    }
    return avg + "/" + total
  }

}
