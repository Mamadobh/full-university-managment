import {Component, Input} from '@angular/core';
import {MatIcon} from "@angular/material/icon";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-page-header',
  standalone: true,
  imports: [
    MatIcon,
    RouterLink
  ],
  templateUrl: './page-header.component.html',
  styleUrl: './page-header.component.scss'
})
export class PageHeaderComponent {
  @Input()
  linkHistory: { label: string, navLink: string }[] = []
  @Input() title: string = "";
  @Input() icon: string = "";


}
