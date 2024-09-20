import {NumberOfSessionResponse} from "../../numberOfSession/model/number-of-session.model";
import {TestTypeResponse} from "../../test-type/model/test-type.model";
import {CoefficientResponse} from "../../coefficient/model/coefficient.model";
import {TestDurationResponse} from "../../test-duration/model/test-duration.model";
import {ModuleTypeResponse} from "../../moduleType/model/module-type.model";
import {LevelDetailsResponseModel} from "../../level/model/LevelDetailsResponse.model";
import {TypeOfSubjectResponse} from "../../typeOfSubject/model/type-of-subject.model";

export class SubjectTypesStudyPlanRequest {
  constructor(
    public typeId: number,
    public numberSessionId: number,
    public id?: number,
    public subjectId?: number
  ) {
  }
}

export class TestStudyPlanRequest {
  constructor(
    public testTypeId: number,
    public coefficient: number,
    public testDuration: number,
    public id?: number
  ) {
  }
}

export class SubjectStudyPlanRequest {
  constructor(
    public name: string,
    public cofficientId: number,
    public tests: TestStudyPlanRequest[],
    public subjectTypes: SubjectTypesStudyPlanRequest[],
    public id?: number,
    public moduleId?: number
  ) {
  }
}

export class ModuleRequestStudyPlan {
  constructor(
    public name: string,
    public description: string,
    public moduleTypeId: number,
    public subjects: SubjectStudyPlanRequest[],
    public id?: number,
    public semesterId?: number
  ) {
  }
}

export class SemesterStudyPlanRequest {
  constructor(
    public name: string,
    public description: string,
    public levelId: number,
    public startDate: Date,
    public endDate: Date,
    public modules: ModuleRequestStudyPlan[],
    public id?: number
  ) {
  }
}

export class StudyPlanRequest {
  constructor(
    public semesters?: SemesterStudyPlanRequest[]
  ) {
  }
}

export class SubjectTypeResoonse {
  constructor(public id: number,
              public type: TypeOfSubjectResponse,
              public numberOfSession: NumberOfSessionResponse
  ) {
  }
}

export class TestResponse {
  constructor(public id: number,
              public testType: TestTypeResponse,
              public coefficient: CoefficientResponse,
              public testDuration: TestDurationResponse
  ) {
  }
}

export class SubjectResponse {
  constructor(public id: number,
              public name: string,
              public coefficient: number,
              public tests: TestResponse[],
              public subjectTypes: SubjectTypeResoonse[]
  ) {
  }

}

export class ModuleResponseStudyPlan {
  constructor(
    public id: number,
    public name: string,
    public description: string,
    public moduleType: ModuleTypeResponse,
    public subjects: SubjectResponse[]
  ) {
  }
}

export class SemesterStudyPlanResponse {
  constructor(public id: number,
              public name: string,
              public description: string,
              public startDate: string,
              public endDate: string,
              public level: LevelDetailsResponseModel,
              public modules: ModuleResponseStudyPlan[]
  ) {
  }

}

export class StudyPlanResponse {
  constructor(public semesters: SemesterStudyPlanResponse[]) {
  }
}
