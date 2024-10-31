import {inject, Injectable} from "@angular/core";
import {StudyPlanRequest, StudyPlanResponse} from "./model/study-plan.model";
import {StudyPlanService} from "./study-plan.service";

@Injectable({
  providedIn: 'root'
})
export class StudyPlanMapper {
  studyPlanService: StudyPlanService = inject(StudyPlanService)

  toStudyPlanForm(studyPlanResponse: StudyPlanResponse) {

    const semesters = studyPlanResponse.semesters.map((semester) => ({
      name: semester.name,
      startDate: new Date(semester.startDate),
      endDate: new Date(semester.endDate),
      description: semester.description,
      modules: semester.modules.map((module) => ({
        name: module.name,
        moduleTypeId: module.moduleType.id,
        description: module.description,
        subjects: module.subjects.map((subject) => ({
          name: subject.name,
          subjectAvg: "",
          coefficientId: subject.coefficient.id,
          tests: subject.tests.map((test) => ({
            testTypeId: test.testType.id,
            coefficientId: test.coefficient.id,
            testDuraionId: test.testDuration.id,
          })),
          subjectTypes: subject.subjectTypes.map((subjectType) => ({
            typeId: subjectType.type.id,
            numberSessionId: subjectType.numberOfSession.id
          }))
        }))
      }))
    }))
    const value = {
      semesters
    }
    this.prepareForm(studyPlanResponse)
    this.studyPlanService.studyPlanFrom.patchValue(value)

  }

  prepareForm(studyPlanResponse: StudyPlanResponse) {

    studyPlanResponse.semesters.forEach((semester, index) => {
      if (index) {
        this.studyPlanService.addSemester()
      }
      semester.modules.forEach((module, indexm) => {
        if (indexm) {
          this.studyPlanService.addModule(index)
        }
        module.subjects.forEach((subject, indexs) => {
          if (indexs) {
            this.studyPlanService.addSubject(index, indexm)
          }
          subject.tests.forEach((test, indext) => {
            if (indext) {
              this.studyPlanService.addTest(index, indexm, indexs)
            }
          })
          subject.subjectTypes.forEach((subjectType, indexst) => {
            if (indexst) {
              this.studyPlanService.addSubjectType(index, indexm, indexs);
            }
          })
        })
      })
    })

  }


}
