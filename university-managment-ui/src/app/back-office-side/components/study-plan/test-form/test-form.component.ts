import {Component, EventEmitter, inject, Input, OnInit, Output, signal} from '@angular/core';
import {AsyncPipe} from "@angular/common";
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from "@angular/material/autocomplete";
import {MatError, MatFormField} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatInput} from "@angular/material/input";
import {AbstractControl, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {AutoCompleteFormComponent} from "../../shared/auto-complete-form/auto-complete-form.component";
import {TestTypeService} from "../../../../core/services/test-type/test-type.service";
import {TestTypeResponse} from "../../../../core/services/test-type/model/test-type.model";
import {CoefficientResponse} from "../../../../core/services/coefficient/model/coefficient.model";
import {TestDurationResponse} from "../../../../core/services/test-duration/model/test-duration.model";
import {CoefficientService} from "../../../../core/services/coefficient/coefficient.service";
import {TestDurationService} from "../../../../core/services/test-duration/test-duration.service";

@Component({
  selector: 'app-test-form',
  standalone: true,
  imports: [
    AsyncPipe,
    MatAutocomplete,
    MatAutocompleteTrigger,
    MatFormField,
    MatIcon,
    MatInput,
    MatOption,
    ReactiveFormsModule,
    AutoCompleteFormComponent,
    MatError
  ],
  templateUrl: './test-form.component.html',
  styleUrl: './test-form.component.scss'
})
export class TestFormComponent implements OnInit {
  _formTest!: FormGroup;
  testTypeService = inject(TestTypeService)
  dataOptionsTestType = signal<TestTypeResponse[]>([])
  coefficientService = inject(CoefficientService)
  dataOptionsCoefficient = signal<CoefficientResponse[]>([])

  testDurationService = inject(TestDurationService)
  dataOptionsTestDuration = signal<TestDurationResponse[]>([])
  errorMsg: string = "field is required !!"

  @Input()
  set formTest(value: AbstractControl) {
    this._formTest = value as FormGroup;
  }

  @Input() testIndex!: number;
  @Output() removeTest = new EventEmitter<number>()
  @Output() addTest = new EventEmitter<number>()

  ngOnInit() {

    this.testTypeService.allTestTypeReponse.subscribe((data) => {
      this.dataOptionsTestType.set(data.content)
    })
    this.testTypeService.findAllTestType()

    this.coefficientService.allCoefficientReponse.subscribe((data) => {
      this.dataOptionsCoefficient.set(data.content)
    })
    this.coefficientService.findAllCoefficient()

    this.testDurationService.allTestDurationReponse.subscribe((data) => {
      this.dataOptionsTestDuration.set(data.content)
    })
    this.testDurationService.findAllTestDuration()
  }

  removeTestEmit(index: number) {
    this.removeTest.emit(index)
  }

  addTestEmit(index: number) {
    this.addTest.emit(index)
  }

  getfield(name: string): FormControl {
    return this._formTest.get(name) as FormControl
  }


  createTestTypeItem(item: string) {
    this.testTypeService.create(
      {testType: item}
    ).subscribe({
      next: (res) => {
        this.testTypeService.findAllTestType()
      }
    })
  }

  createCoefficientItem(item: string) {
    this.coefficientService.create(
      {coefficient: +item}
    ).subscribe({
      next: (res) => {
        this.coefficientService.findAllCoefficient()
      }
    })
  }

  createTestDurationsItem(item: string) {
    this.testDurationService.create(
      {testDuration: +item}
    ).subscribe({
      next: (res) => {
        this.testDurationService.findAllTestDuration()
      }
    })
  }

  displayFnTestType(testType: TestTypeResponse): string {
    return testType && testType.testType ? testType.testType : '';
  }

  displayFnCoefficient(coefficient: CoefficientResponse): string {
    return coefficient && coefficient.coefficient ? coefficient.coefficient.toString() : '';
  }

  displayFnTestDuration(testDuration: TestDurationResponse): string {
    return testDuration && testDuration.testDuration ? testDuration.testDuration.toString() : '';
  }






}
