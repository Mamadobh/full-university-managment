import {Component, EventEmitter, inject, Input, Output, signal} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";

import {MatIcon} from "@angular/material/icon";
import {AutoCompleteFormComponent} from "../../shared/auto-complete-form/auto-complete-form.component";
import {MatError} from "@angular/material/form-field";
import {JsonPipe} from "@angular/common";
import {TypeOfSubjectService} from "../../../../core/services/typeOfSubject/type-of-subject.service";
import {NumberOfSessionService} from "../../../../core/services/numberOfSession/number-of-session.service";
import {
  TypeOfSubjectRequest,
  TypeOfSubjectResponse
} from "../../../../core/services/typeOfSubject/model/type-of-subject.model";
import {NumberOfSessionResponse} from "../../../../core/services/numberOfSession/model/number-of-session.model";

@Component({
  selector: 'app-subject-type-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatIcon,
    AutoCompleteFormComponent,
    MatError,
    JsonPipe
  ],
  templateUrl: './subject-type-form.component.html',
  styleUrl: './subject-type-form.component.scss'
})
export class SubjectTypeFormComponent {
  _formSubjectType!: FormGroup;
  typeOfSubjectService = inject(TypeOfSubjectService)
  dataOptionsTypeOfSubject = signal<TypeOfSubjectResponse[]>([])

  numberOfSessionService = inject(NumberOfSessionService)
  dataOptionsNumberOfSession = signal<NumberOfSessionResponse[]>([])
  errorMsg: string = "field is required !!"
  duplicationError="Type of subject is duplicated"
  @Input()
  set formSubjectType(value: AbstractControl) {
    this._formSubjectType = value as FormGroup;
  }

  @Input() subjectTypeIndex!: number;

  @Output()
  removeSubjectType = new EventEmitter<number>()
  @Output()
  addSubjectType = new EventEmitter<number>()

  ngOnInit() {

    this.typeOfSubjectService.alltTypeOfSubjectReponse.subscribe((data) => {
      this.dataOptionsTypeOfSubject.set(data.content)
    })
    this.typeOfSubjectService.findAllTestOfSubject()

    this.numberOfSessionService.allNumberOfSessionReponse.subscribe((data) => {
      this.dataOptionsNumberOfSession.set(data.content)
    })
    this.numberOfSessionService.findAllNumberOfSession()
  }

  removeSubjectTypeEmit(index: number) {
    this.removeSubjectType.emit(index)
  }

  addSubjectTypeEmit(index: number) {
    this.addSubjectType.emit(index)
  }

  createTypeOfSubjectItem(item: string) {
    this.typeOfSubjectService.create(
      {subjectType: item}
    ).subscribe({
      next: (res) => {
        this.typeOfSubjectService.findAllTestOfSubject()
      }
    })
  }

  createNumberOfSessionItem(item: string) {
    this.numberOfSessionService.create(
      {numberOfSession: +item}
    ).subscribe({
      next: (res) => {
        this.numberOfSessionService.findAllNumberOfSession()
      }
    })
  }

  displayFnTypeOfSubject(typeOfSubject: TypeOfSubjectRequest): string {
    return typeOfSubject && typeOfSubject.subjectType ? typeOfSubject.subjectType : '';
  }

  displayFnNumberOfSession(numberOfSession: NumberOfSessionResponse): string {
    return numberOfSession && numberOfSession.numberOfSession ? numberOfSession.numberOfSession.toString() : '';
  }

  getfield(name: string): FormControl {
    return this._formSubjectType.get(name) as FormControl
  }

}
