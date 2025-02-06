import {Component, computed, inject, OnInit, signal, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MatFabButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {StudyPlanService} from "../../../../core/services/study-plan/study-plan.service";
import {StudyPlanRequest, StudyPlanResponse} from "../../../../core/services/study-plan/model/study-plan.model";
import {JsonPipe} from "@angular/common";
import {StudyPlanMapper} from "../../../../core/services/study-plan/study-plan-mapper.service";
import {BASE_ADMIN_ROUTE} from "../../../../core/Constants";
import {SemesterFormComponent} from "../../../components/study-plan/semester-form/semester-form.component";
import {PageHeaderComponent} from "../../../components/shared/page-header/page-header.component";
import {SubjectFormComponent} from "../../../components/study-plan/subject-form/subject-form.component";


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
  status: string = "create"
  mapper: StudyPlanMapper = inject(StudyPlanMapper)
  studyPlanReponse = signal<StudyPlanResponse>(new StudyPlanResponse([]));

  constructor() {

  }

  ngOnInit() {
    this.activeRoute.paramMap.subscribe((data) => {

      this.levelId = data.get("levelId") as string

      this.linkHistory.set([
        {label: "Study plan", navLink: BASE_ADMIN_ROUTE+"/study-plan"},
        {label: "Overview", navLink:BASE_ADMIN_ROUTE+ "/study-plan/" + data.get("levelId")},
        {label: "Create", navLink: BASE_ADMIN_ROUTE+"/study-plan/" + data.get("levelId") + "/create"},
      ])
    })


    this.activeRoute.queryParams.subscribe((data) => {
      this.status = data['status'];
    })

    this.studyPlanService.studyPlanFrom = this.studyPlanService.createForm("init")

    if (this.status == "edit") {
      console.log("level id ==", this.levelId)
      this.findStudyplan(+this.levelId)
      console.log("studyPlan response111 ", this.studyPlanReponse())
    }

    this.studyPlanService.studyPlanFrom?.valueChanges.subscribe((data) => {
      console.log(data)
    })

  }

  findStudyplan(id: number) {
    this.studyPlanService.findStudyPlan(id).subscribe({
      next: (res) => {
        this.studyPlanReponse.set(res.data)
        this.mapper.toStudyPlanForm(this.studyPlanReponse());
        console.log("studyPlan response22 ", this.studyPlanReponse())

      }
    })
  }

  saveStudyPlan(request: StudyPlanRequest) {

    this.studyPlanService.saveStudyPlan(request).subscribe(({
      next: (res) => {
        this.success()

        this.errorsMessage = []
      },
      error: (err) => {
        this.error(err)
      }
    }))
  }

  private success() {
    this.router.navigate(["back-office","views","study-plan", this.levelId, "recap"])
    this.studyPlanService.studyPlanFrom.reset()
    this.errorsMessage = []
  }

  private error(err: any) {
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

  updateStudtPlan(request: StudyPlanRequest) {
    this.studyPlanService.updateStudyPlan(request).subscribe(({
      next: (res) => {
        this.success()

        console.log("res = ", res)
      },
      error: (err) => {
        this.error(err)
        console.log("err = ", err)
      }
    }))
  }

  onSubmit(event: Event) {
    event.preventDefault()
    this.studyPlanService.isFormSubmited.set(true)
    this.studyPlanService.studyPlanFrom.markAllAsTouched()
    let studyPlanRequest = this.form().value as StudyPlanRequest;
    studyPlanRequest.semesters = studyPlanRequest.semesters?.map((el: any) => {
      return {
        startDate: el?.startDate?.toISOString().split('T')[0],
        endDate: el?.endDate?.toISOString().split('T')[0],
        levelId: +this.levelId,
        name: el?.name,
        description: el?.description,
        modules: el?.modules
      };
    }) ?? [];

    if (!this.form().invalid) {
      if (this.status == "create") {
        console.log('create')
        this.saveStudyPlan(studyPlanRequest)
      } else {
        console.log("ediiiit")
        this.updateStudtPlan(studyPlanRequest)
      }
    }
  }


}
