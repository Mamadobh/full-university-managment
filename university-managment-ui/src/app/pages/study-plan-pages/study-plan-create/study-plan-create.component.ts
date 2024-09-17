import {Component, computed, inject, OnInit, signal, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {SemesterFormComponent} from "../../../components/study-plan/semester-form/semester-form.component";
import {PageHeaderComponent} from "../../../components/shared/page-header/page-header.component";
import {MatFabButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {ReactiveFormsModule} from "@angular/forms";
import {StudyPlanService} from "../../../core/services/study-plan/study-plan.service";
import {SubjectFormComponent} from "../../../components/study-plan/subject-form/subject-form.component";

export interface User {
  name: string;
}

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
    SubjectFormComponent
  ],
  encapsulation: ViewEncapsulation.None

})
export class StudyPlanCreateComponent implements OnInit {
  linkHistory = signal<{ label: string, navLink: string }[]>([])
  private activeRoute = inject(ActivatedRoute)
  private router = inject(Router)
  levelId = ""
  studyPlanService = inject(StudyPlanService)

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


  onSubmit() {
    this.studyPlanService.isFormSubmited.set(true)
    this.studyPlanService.studyPlanFrom.markAllAsTouched()

    console.log("the form is submited !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
  }
}
