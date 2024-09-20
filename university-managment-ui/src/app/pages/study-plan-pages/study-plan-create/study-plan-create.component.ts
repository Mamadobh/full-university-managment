import {Component, computed, inject, OnInit, signal, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {SemesterFormComponent} from "../../../components/study-plan/semester-form/semester-form.component";
import {PageHeaderComponent} from "../../../components/shared/page-header/page-header.component";
import {MatFabButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {StudyPlanService} from "../../../core/services/study-plan/study-plan.service";
import {SubjectFormComponent} from "../../../components/study-plan/subject-form/subject-form.component";
import {StudyPlanRequest} from "../../../core/services/study-plan/model/study-plan.model";
import {data} from "autoprefixer";
import {JsonPipe} from "@angular/common";


@Component({
  selector: 'app-study-plan-create',
  templateUrl: './study-plan-create.component.html',
  styleUrl: './study-plan-create.component.scss',
  standalone: true,
  imports: [

    SemesterFormComponent,
    PageHeaderComponent,
    MatFabButton,
    MatIcon,
    ReactiveFormsModule,
    FormsModule,
    SubjectFormComponent,
    JsonPipe
  ],
  encapsulation: ViewEncapsulation.None

})
export class StudyPlanCreateComponent implements OnInit {
  linkHistory = signal<{ label: string, navLink: string }[]>([])
  private activeRoute = inject(ActivatedRoute)
  private router = inject(Router)
  levelId = ""
  studyPlanService = inject(StudyPlanService)
  form = computed(() => this.studyPlanService.studyPlanFrom)
  errorsMessage: string[] = []

  constructor() {
    this.studyPlanService.studyPlanFrom = this.studyPlanService.createForm("init")
    this.studyPlanService.studyPlanFrom?.valueChanges.subscribe((data) => {
      console.log(data)
    })
  }

  ngOnInit() {

    this.activeRoute.paramMap.subscribe((data) => {

      this.levelId = data.get("levelId") as string

      this.linkHistory.set([
        {label: "Study plan", navLink: "/study-plan"},
        {label: "Overview", navLink: "/study-plan/" + data.get("levelId")},
        {label: "Create", navLink: "/study-plan/" + data.get("levelId") + "/create"},
      ])
    })
  }

  saveStudyPlan(request: StudyPlanRequest) {

    this.studyPlanService.saveStudyPlan(request).subscribe(({
      next: (res) => {
        this.router.navigate(["study-plan",this.levelId,"recap"])
        this.studyPlanService.studyPlanFrom.reset()

        console.log("res = ", res)
        this.errorsMessage = []
      },
      error: (err) => {
        console.log("err = ",err)
        setTimeout(() => {
          window.scrollBy(0, 100);

        }, 100)
        if (err.error.error?.errors) {
          this.errorsMessage = [...this.errorsMessage, ...Object.values(err.error.error?.errors) as string []]
        }
        if (err.error.error?.error) {
          this.errorsMessage.push(err.error.error?.error)
        }
      }
    }))
  }

  onSubmit(event:Event) {
    event.preventDefault()
    this.studyPlanService.isFormSubmited.set(true)
    this.studyPlanService.studyPlanFrom.markAllAsTouched()
    let my_form = this.form().value as StudyPlanRequest;
    my_form.semesters = my_form.semesters?.map((el: any) => {
      return {
        startDate: el?.startDate?.toISOString().split('T')[0],
        endDate: el?.endDate?.toISOString().split('T')[0],
        levelId: +this.levelId,
        name: el?.name,
        description:el?.description,
        modules: el?.modules
      };
    }) ?? [];
    console.log("my f ", my_form)
    console.log("this form is ", my_form)

    if (!this.form().invalid) {
      console.log("this form is ", my_form)
      this.saveStudyPlan(my_form)
    }
  }


}
