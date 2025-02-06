import {Component, inject, OnInit, signal} from '@angular/core';
import {MatButton, MatFabButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {SafeUrlPipe} from "../../../../pipes/safe-url.pipe";
import {JsonPipe, NgComponentOutlet} from "@angular/common";
import {LevelService} from "../../../../core/services/level/level.service";
import {LevelResponse} from "../../../../core/services/level/model/LevelDetailsResponse.model";
import {BASE_ADMIN_ROUTE} from "../../../../core/Constants";
import {PageHeaderComponent} from "../../../components/shared/page-header/page-header.component";


@Component({
  selector: 'app-study-plan-display',
  standalone: true,
  imports: [
    MatButton,
    MatIcon,
    MatFabButton,
    PageHeaderComponent,
    RouterLink,
    SafeUrlPipe,
    NgComponentOutlet,
    JsonPipe,


  ],
  templateUrl: './study-plan-display.component.html',
  styleUrl: './study-plan-display.component.scss'
})
export class StudyPlanDisplayComponent implements OnInit {
  linkHistory = signal<{ label: string, navLink: string }[]>([])
  private activeRoute = inject(ActivatedRoute)
  private levelService = inject(LevelService)
  levelId: string = "";
  pdfUrl: string = '';
  errorMessage = ""
  currentLevel = signal<LevelResponse>(new LevelResponse())
  baseUrl=BASE_ADMIN_ROUTE

  ngOnInit() {

    this.activeRoute.paramMap.subscribe((data) => {
      this.levelId = data.get("levelId") as string
      this.linkHistory.set([{label: "Study plan", navLink: BASE_ADMIN_ROUTE+"/study-plan"}, {
        label: "Overview",
        navLink: BASE_ADMIN_ROUTE+"/study-plan/" + data.get("levelId")
      },])
    })
    this.findLevel(+this.levelId)
  }

  loadPdf(studyPlan: any): void {
    if (studyPlan) {
      const blob = new Blob([studyPlan], {type: 'application/pdf'});
      this.pdfUrl = URL.createObjectURL(blob);
    }

  }

  private findLevel(id: number) {
    this.levelService.get(id).subscribe({
      next: (res) => {
        this.currentLevel.set(res.data)
        this.pdfUrl=this.levelService.getStudyPlanPdf(id)
      },
      error: (err) => {

      }
    })
  }
}
