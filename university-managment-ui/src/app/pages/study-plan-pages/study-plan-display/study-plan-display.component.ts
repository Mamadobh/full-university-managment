import {Component, inject, OnInit, signal} from '@angular/core';
import {MatButton, MatFabButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {PageHeaderComponent} from "../../../components/shared/page-header/page-header.component";
import {ActivatedRoute, RouterLink} from "@angular/router";

@Component({
  selector: 'app-study-plan-display',
  standalone: true,
  imports: [
    MatButton,
    MatIcon,
    MatFabButton,
    PageHeaderComponent,
    RouterLink
  ],
  templateUrl: './study-plan-display.component.html',
  styleUrl: './study-plan-display.component.scss'
})
export class StudyPlanDisplayComponent implements OnInit {
  linkHistory = signal<{ label: string, navLink: string }[]>([])
  private activeRoute = inject(ActivatedRoute)
  levelId: string = "";

  ngOnInit() {

      this.activeRoute.paramMap.subscribe((data) => {
      this.levelId = data.get("levelId") as string
      this.linkHistory.set([{label: "Study plan", navLink: "/study-plan"}, {
        label: "Overview",
        navLink: "/study-plan/" + data.get("levelId")
      },])
    })
  }
}
