import {Component, inject, OnInit, signal} from '@angular/core';
import {MatButton, MatFabButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {PageHeaderComponent} from "../../../components/shared/page-header/page-header.component";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-study-plan-display',
  standalone: true,
  imports: [
    MatButton,
    MatIcon,
    MatFabButton,
    PageHeaderComponent
  ],
  templateUrl: './study-plan-display.component.html',
  styleUrl: './study-plan-display.component.scss'
})
export class StudyPlanDisplayComponent implements OnInit {
  linkHistory = signal<{ label: string, navLink: string }[]>([])
  private activeRoute = inject(ActivatedRoute)

  ngOnInit() {
    this.activeRoute.paramMap.subscribe((data) => {
      this.linkHistory.set([{label: "Study plan", navLink: "/study-plan"}, {
        label: "Overview",
        navLink: "/study-plan/" + data.get("levelId")
      },])
    })
  }
}
