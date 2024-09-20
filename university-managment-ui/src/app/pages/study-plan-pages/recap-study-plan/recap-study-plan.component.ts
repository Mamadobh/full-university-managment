import {Component, computed, inject, OnInit, signal, Signal} from '@angular/core';
import {
  SemesterStudyPlanResponse,
  StudyPlanResponse,
  TestResponse
} from "../../../core/services/study-plan/model/study-plan.model";
import {StudyPlanService} from "../../../core/services/study-plan/study-plan.service";
import {ActivatedRoute} from "@angular/router";
import {LevelDetailsResponseModel} from "../../../core/services/level/model/LevelDetailsResponse.model";
import {TypeOfSubjectService} from "../../../core/services/typeOfSubject/type-of-subject.service";
import {TypeOfSubjectResponse} from "../../../core/services/typeOfSubject/model/type-of-subject.model";
import {JsonPipe} from "@angular/common";
import {SESSION_DURATION_H} from "../../../core/Constants";
import {MatFabButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-recap-study-plan',
  standalone: true,
  imports: [
    JsonPipe,
    MatFabButton,
    MatIcon
  ],
  templateUrl: './recap-study-plan.component.html',
  styleUrl: './recap-study-plan.component.scss'
})
export class RecapStudyPlanComponent implements OnInit {
  title: string = "studyPlan Recap"
  studyPlanReponse = signal<StudyPlanResponse>(new StudyPlanResponse([]));
  studyPlanService: StudyPlanService = inject(StudyPlanService)
  activatedRoute: ActivatedRoute = inject(ActivatedRoute)
  subjectTypeService: TypeOfSubjectService = inject(TypeOfSubjectService)
  levelId!: number;
  level: Signal<LevelDetailsResponseModel | null> = computed(() => {
    if (this.studyPlanReponse() && this.studyPlanReponse().semesters.length > 0) {
      return this.studyPlanReponse().semesters[0].level;
    }
    return null;
  });

  columnsArray: { column: string, accessor: string, rowspan: boolean, withTotal: boolean }[] = [
    {
      column: "N°",
      accessor: "n",
      rowspan: true,
      withTotal: false,
    },
    {
      column: "Module",
      accessor: "module",
      rowspan: true,
      withTotal: false,
    },
    {
      column: "Module Type",
      accessor: "module_type",
      rowspan: true,
      withTotal: false,
    },
    {
      column: "Subjects",
      accessor: "subject",
      rowspan: false,
      withTotal: false,
    },
    {
      column: "Coef.S",
      accessor: "coefficient",
      rowspan: false
      ,
      withTotal: true,
    },
    {
      column: "Coef.M",
      accessor: "coefficientm",
      rowspan: true
      ,
      withTotal: false,
    }
    , {
      column: "Average",
      accessor: "average",
      rowspan: false
      ,
      withTotal: false,
    }
  ]

  subjectTypes: TypeOfSubjectResponse[] = []
  data = computed(() => {
    console.log("hi data ", this.transformData())
    return this.transformData()
  })

  columns = computed(() => {
    const types = this.subjectTypes.map(el => {
      return {
        column: "H." + el.subjectType,
        accessor: el.subjectType.toLowerCase(),
        rowspan: false,
        withTotal: true
      }

    })
    this.columnsArray.splice(4, 0, ...types)
    return this.columnsArray;
  })

  getPeriod(date: string) {
    return date.substring(5)
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(data => {
      const levelIdParam = data.get("levelId");
      this.levelId = levelIdParam ? parseInt(levelIdParam) : 0
    })
    this.subjectTypeService.alltTypeOfSubjectReponse.subscribe((data) => {
      console.log(data)
      this.subjectTypes = data.content
    })


    if (this.levelId) {
      this.findStudyplan(this.levelId)
    }
    this.findSubjectType()

  }

  findStudyplan(id: number) {
    this.studyPlanService.findStudyPlan(id).subscribe({
      next: (res) => {
        this.studyPlanReponse.set(res.data)
        console.log(this.studyPlanReponse())
      }
    })
  }

  findSubjectType() {
    this.subjectTypeService.findAllTestOfSubject()
  }

  initializeSubjectTypes(): { [key: string]: number } {
    return this.subjectTypes.reduce((acc, type) => {
      acc[type.subjectType.toLowerCase()] = 0.00;
      return acc;
    }, {} as { [key: string]: number });
  }


  getAvreage(tests: TestResponse[]): string {
    const avg = tests
      .map(test => {
        const coef = test.coefficient.coefficient > 1 ? test.coefficient.coefficient.toString() : "";
        return `${coef}*${test.testType.testType}`;
      })
      .join("+");

    return `(${avg})`;
  }


  getTotal(index: number, col: string): number {
    const modules = this.data()[index]?.modules;

    if (!modules) return 0

    return modules.reduce((moduleTotal: number, {subjects}) => {
      return moduleTotal + subjects.reduce((subjectTotal: number, subject) => {
        const value = subject[col];
        return subjectTotal + (typeof value === 'number' ? value : 0);
      }, 0);
    }, 0);
  }

  transformData() {
    return this.studyPlanReponse().semesters.map(sem => ({

          semester: sem.name,
          id:
          sem.id,
          modules:
            sem.modules.map((mod) => {

              const subjects = mod.subjects.map(sub => {
                const subjectData: { [key: string]: any } = {
                  subject: sub.name,
                  coefficient: sub.coefficient,
                  average: this.getAvreage(sub.tests),
                  ...this.initializeSubjectTypes()
                };

                sub.subjectTypes.forEach(el => {
                  subjectData[el.type.subjectType.toLowerCase()] = parseFloat(el.numberOfSession.numberOfSession.toFixed(2))*1.5;
                });

                return subjectData;
              });

              return {
                module: mod.name,
                module_type: mod.moduleType.type,
                coefficientm: mod.subjects.reduce((total, sub) => total + sub.coefficient, 0),
                subjects
              };
            })
        }
      )
    )
      ;
  }

  getTotalHour(index: number): number {
    let total = 0
    this.columns().forEach(el => {
      if (el.column.startsWith('H')) {
       total+= this.getTotal(index, el.accessor)
      }
    })
    return total;
  }

  getValue(col: any, mod: any, sub: any): any {
    return col.rowspan ? mod[col.accessor] : sub[col.accessor];

  }

}
