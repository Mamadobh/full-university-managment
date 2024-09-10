import {Injectable} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import * as _moment from 'moment';
import {default as _rollupMoment} from 'moment';

const moment = _rollupMoment || _moment;

@Injectable({
  providedIn: 'root'
})
export class StudyPlanService {

  studyPlanFrom!: FormGroup;

  constructor(private fb: FormBuilder) {


  }

  createFrom(itemType: string): FormGroup {
    let formItem: FormGroup = this.fb.group({});
    switch (itemType) {
      case"init":
        formItem = this.fb.group({
          smesterName: "",
          startDate: moment(),
          endDate: moment(),
          semesterDescription: "",
          modules: this.fb.array([
            this.fb.group({
                moduleName: "",
                moduleType: "",
                moduleDescription: "",
                subjects: this.fb.array([
                  this.fb.group({
                    subjectName: "",
                    subjectAvg: "",
                    subjectCoef: "",
                    tests: this.fb.array([
                      this.fb.group({
                        testType: "",
                        testCoef: "",
                        testNbh: ""
                      })
                    ]),
                    subjectTypes: this.fb.array([
                      this.fb.group({
                        subjectType: "",
                        subjectTypeNbh: ""
                      })
                    ])
                  })
                ])

              }
            )
          ])
        })
        break;
      case"module":
        formItem = this.fb.group({
            moduleName: "",
            moduleType: "",
            moduleDescription: "",
            subjects: this.fb.array([
              this.fb.group({
                subjectName: "",
                subjectAvg: "",
                subjectCoef: "",
                tests: this.fb.array([
                  this.fb.group({
                    testType: "",
                    testCoef: "",
                    testNbh: ""
                  })
                ]),
                subjectTypes: this.fb.array([
                  this.fb.group({
                    subjectType: "",
                    subjectTypeNbh: ""
                  })
                ])
              })
            ])

          }
        )
        break;
      case "subject":
        formItem = this.fb.group({
          subjectName: "",
          subjectAvg: "",
          subjectCoef: "",
          tests: this.fb.array([
            this.fb.group({
              testType: "",
              testCoef: "",
              testNbh: ""
            })
          ]),
          subjectTypes: this.fb.array([
            this.fb.group({
              subjectType: "",
              subjectTypeNbh: ""
            })]),
        })
        break;
      case "test":
        formItem = this.fb.group({
          testType: "",
          testCoef: "",
          testNbh: ""
        })
        break;
      case "subjectType":
        formItem = this.fb.group({
          subjectType: "",
          subjectTypeNbh: ""
        })
    }
    return formItem
  }

  getAllModules(): FormArray {
    return this.studyPlanFrom.get("modules") as FormArray
  }

  addModule() {
    this.getAllModules().push(this.createFrom("module"))

  }

  removeModule(moduleIndex: number) {
    if (this.getAllModules().length > 1) {
      this.getAllModules().removeAt(moduleIndex);
    }
  }

  getAllSubject(moduleIndex: number): FormArray {
    return this.getAllModules().at(moduleIndex).get("subjects") as FormArray
  }

  addSubject(moduleIndex: number) {
    this.getAllSubject(moduleIndex).push(this.createFrom("subject"))
  }

  removeSubject(moduleIndex: number, subjectIndex: number) {
    if (this.getAllSubject(moduleIndex).length > 1) {
      this.getAllSubject(moduleIndex).removeAt(subjectIndex)
    }
  }

  getAllTests(moduleIndex: number, subjectIndex: number): FormArray {
    return this.getAllSubject(moduleIndex).at(subjectIndex).get("tests") as FormArray
  }

  addTest(moduleIndex: number, subjectIndex: number) {
    this.getAllTests(moduleIndex, subjectIndex).push(this.createFrom("test"))
  }

  removeTest(moduleIndex: number, subjectIndex: number, testIndex: number) {
    if (this.getAllTests(moduleIndex, subjectIndex).length > 1) {
      this.getAllTests(moduleIndex, subjectIndex).removeAt(testIndex);
    }
  }

  getAllTypeSubjects(moduleIndex: number, subjectIndex: number): FormArray {
    return this.getAllSubject(moduleIndex).at(subjectIndex).get("subjectTypes") as FormArray
  }

  addSubjectType(moduleIndex: number, subjectIndex: number) {
    this.getAllTypeSubjects(moduleIndex, subjectIndex).push(this.createFrom("subjectType"))
  }

  removeSubjectType(moduleIndex: number, subjectIndex: number, subjectTypeIndex: number) {
    if (this.getAllTypeSubjects(moduleIndex, subjectIndex).length > 1) {
      this.getAllTypeSubjects(moduleIndex, subjectIndex).removeAt(subjectTypeIndex)
    }
  }
}
