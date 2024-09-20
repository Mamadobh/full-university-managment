import {Injectable, signal} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import * as _moment from 'moment';
import {default as _rollupMoment} from 'moment';
import {ValidateDate, validateDuplication} from "../../../valildators/validators";
import {StudyPlanRequest, StudyPlanResponse} from "./model/study-plan.model";
import {HttpClient} from "@angular/common/http";
import {BASE_PATH} from "../../Constants";
import {ResponseModel} from "../../model/Response.model";
import {Observable} from "rxjs";

const moment = _rollupMoment || _moment;

@Injectable({
  providedIn: 'root'
})
export class StudyPlanService {

  studyPlanFrom!: FormGroup;
  isFormSubmited = signal(false)
  _path: string = BASE_PATH + "levels" + "/study-plan"

  constructor(private fb: FormBuilder, private _http: HttpClient) {

  }

  saveStudyPlan = (studyPlanRequest: StudyPlanRequest) => {
    return this._http.post<number>(this._path, studyPlanRequest)
  }

  findStudyPlan(id: number): Observable<ResponseModel<StudyPlanResponse>> {
    return this._http.get<ResponseModel<StudyPlanResponse>>(this._path+"/"+id);
  }

  createForm(itemType: string): FormGroup {
    let formItem: FormGroup = this.fb.group({});
    switch (itemType) {
      case "init":
        formItem = this.fb.group(
          {
            semesters: this.fb.array([
              this.fb.group({
                  name: ["", {validators: [Validators.required], updateOn: 'blur'}],
                  startDate: new Date(),
                  endDate: new Date(),
                  description: ["", {validators: [Validators.required], updateOn: 'blur'}],
                  modules: this.fb.array([
                    this.fb.group({
                      name: ["", {validators: [Validators.required], updateOn: 'blur'}],
                      moduleTypeId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                      description: "",
                      subjects: this.fb.array([
                        this.fb.group({
                          name: ["", {validators: [Validators.required], updateOn: 'blur'}],
                          subjectAvg: ["", {updateOn: 'blur'}],
                          coefficientId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                          tests: this.fb.array([
                            this.fb.group({
                              testTypeId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                              coefficientId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                              testDuraionId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                            })
                          ]),
                          subjectTypes: this.fb.array([
                            this.fb.group({
                              typeId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                              numberSessionId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                            })
                          ], {validators: validateDuplication("typeId")})
                        })
                      ], {validators: validateDuplication("name")})
                    })
                  ], {validators: validateDuplication("name")})
                },
                {validators: ValidateDate})
            ], {validators: validateDuplication("name")})
          }
        );
        break;
      case "semester":
        formItem = this.fb.group({
            name: ["", {validators: [Validators.required], updateOn: 'blur'}],
            startDate: new Date(),
            endDate: new Date(),
            description: ["", {validators: [Validators.required], updateOn: 'blur'}],
            modules: this.fb.array([
              this.fb.group({
                name: ["", {validators: [Validators.required], updateOn: 'blur'}],
                moduleTypeId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                description: "",
                subjects: this.fb.array([
                  this.fb.group({
                    name: ["", {validators: [Validators.required], updateOn: 'blur'}],
                    subjectAvg: ["", {updateOn: 'blur'}],
                    coefficientId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                    tests: this.fb.array([
                      this.fb.group({
                        testTypeId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                        coefficientId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                        testDuraionId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                      })
                    ]),
                    subjectTypes: this.fb.array([
                      this.fb.group({
                        typeId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                        numberSessionId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                      })
                    ], {validators: validateDuplication("typeId")})
                  })
                ], {validators: validateDuplication("name")})
              })
            ], {validators: validateDuplication("name")})
          },
          {validators: ValidateDate})
        break;
      case "module":
        formItem = this.fb.group({
          name: ["", {validators: [Validators.required], updateOn: 'blur'}],
          moduleTypeId: ["", {validators: [Validators.required], updateOn: 'blur'}],
          description: "",
          subjects: this.fb.array([
            this.fb.group({
              name: ["", {validators: [Validators.required], updateOn: 'blur'}],
              subjectAvg: ["", {updateOn: 'blur'}],
              coefficientId: ["", {validators: [Validators.required], updateOn: 'blur'}],
              tests: this.fb.array([
                this.fb.group({
                  testTypeId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                  coefficientId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                  testDuraionId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                })
              ]),
              subjectTypes: this.fb.array([
                this.fb.group({
                  typeId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                  numberSessionId: ["", {validators: [Validators.required], updateOn: 'blur'}],
                })
              ], {validators: validateDuplication("typeId")})
            })
          ], {validators: validateDuplication("name")})
        })

        break;
      case "subject":
        formItem = this.fb.group({
          name: ["", {validators: [Validators.required], updateOn: 'blur'}],
          subjectAvg: ["", {updateOn: 'blur'}],
          coefficientId: ["", {validators: [Validators.required], updateOn: 'blur'}],
          tests: this.fb.array([
            this.fb.group({
              testTypeId: ["", {validators: [Validators.required], updateOn: 'blur'}],
              coefficientId: ["", {validators: [Validators.required], updateOn: 'blur'}],
              testDuraionId: ["", {validators: [Validators.required], updateOn: 'blur'}],
            })
          ]),
          subjectTypes: this.fb.array([
            this.fb.group({
              typeId: ["", {validators: [Validators.required], updateOn: 'blur'}],
              numberSessionId: ["", {validators: [Validators.required], updateOn: 'blur'}],
            })
          ], {validators: validateDuplication("typeId")})
        })
        break;
      case "test":
        formItem = this.fb.group({
          testTypeId: ["", {validators: [Validators.required], updateOn: 'blur'}],
          coefficientId: ["", {validators: [Validators.required], updateOn: 'blur'}],
          testDuraionId: ["", {validators: [Validators.required], updateOn: 'blur'}],
        })
        break;
      case "subjectType":
        formItem = this.fb.group({
          typeId: ["", {validators: [Validators.required], updateOn: 'blur'}],
          numberSessionId: ["", {validators: [Validators.required], updateOn: 'blur'}],
        })
        break;
    }
    return formItem;
  }

  getAllSemesters(): FormArray {
    return this.studyPlanFrom.get("semesters") as FormArray
  }

  addSemester() {
    if (this.getAllSemesters().length < 2) {
      this.getAllSemesters().push(this.createForm("semester"))
    }
  }

  removeSemester(semesterIndex: number) {
    if (this.getAllSemesters().length > 1) {
      this.getAllSemesters().removeAt(semesterIndex)
    }
  }

  getAllModules(semesterIndex: number): FormArray {
    return this.getAllSemesters().at(semesterIndex).get("modules") as FormArray
  }

  addModule(semesterIndex: number) {
    this.getAllModules(semesterIndex).push(this.createForm("module"))

  }

  removeModule(semesterIndex: number, moduleIndex: number) {
    if (this.getAllModules(semesterIndex).length > 1) {
      this.getAllModules(semesterIndex).removeAt(moduleIndex);
    }
  }

  getAllSubject(semesterIndex: number, moduleIndex: number): FormArray {
    return this.getAllModules(semesterIndex).at(moduleIndex).get("subjects") as FormArray
  }

  addSubject(semesterIndex: number, moduleIndex: number) {
    this.getAllSubject(semesterIndex, moduleIndex).push(this.createForm("subject"))
  }

  removeSubject(semesterIndex: number, moduleIndex: number, subjectIndex: number) {
    if (this.getAllSubject(semesterIndex, moduleIndex).length > 1) {
      this.getAllSubject(semesterIndex, moduleIndex).removeAt(subjectIndex)
    }
  }

  getAllTests(semesterIndex: number, moduleIndex: number, subjectIndex: number): FormArray {
    return this.getAllSubject(semesterIndex, moduleIndex).at(subjectIndex).get("tests") as FormArray
  }

  addTest(semesterIndex: number, moduleIndex: number, subjectIndex: number) {
    this.getAllTests(semesterIndex, moduleIndex, subjectIndex).push(this.createForm("test"))
  }

  removeTest(semesterIndex: number, moduleIndex: number, subjectIndex: number, testIndex: number) {
    if (this.getAllTests(semesterIndex, moduleIndex, subjectIndex).length > 1) {
      this.getAllTests(semesterIndex, moduleIndex, subjectIndex).removeAt(testIndex);
    }
  }

  getAllTypeSubjects(semesterIndex: number, moduleIndex: number, subjectIndex: number): FormArray {
    return this.getAllSubject(semesterIndex, moduleIndex).at(subjectIndex).get("subjectTypes") as FormArray
  }

  addSubjectType(semesterIndex: number, moduleIndex: number, subjectIndex: number) {
    this.getAllTypeSubjects(semesterIndex, moduleIndex, subjectIndex).push(this.createForm("subjectType"))
  }

  removeSubjectType(semesterIndex: number, moduleIndex: number, subjectIndex: number, subjectTypeIndex: number) {
    if (this.getAllTypeSubjects(semesterIndex, moduleIndex, subjectIndex).length > 1) {
      this.getAllTypeSubjects(semesterIndex, moduleIndex, subjectIndex).removeAt(subjectTypeIndex)
    }
  }


}
