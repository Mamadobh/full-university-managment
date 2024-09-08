import {Component, inject, OnInit, signal, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {SemesterFormComponent} from "../../../components/study-plan/semester-form/semester-form.component";
import {PageHeaderComponent} from "../../../components/shared/page-header/page-header.component";

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
    PageHeaderComponent
  ],
  encapsulation: ViewEncapsulation.None

})
export class StudyPlanCreateComponent implements OnInit {
  linkHistory = signal<{ label: string, navLink: string }[]>([])
  private activeRoute = inject(ActivatedRoute)
  private router = inject(Router)
  levelId = ""


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


}
