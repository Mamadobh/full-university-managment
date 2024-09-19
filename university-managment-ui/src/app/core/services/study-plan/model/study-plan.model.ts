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
