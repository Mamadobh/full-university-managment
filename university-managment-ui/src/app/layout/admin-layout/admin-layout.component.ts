import {Component, computed, signal} from '@angular/core';
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatSidenav, MatSidenavContainer, MatSidenavContent} from "@angular/material/sidenav";
import {MatToolbar} from "@angular/material/toolbar";
import {RouterOutlet} from "@angular/router";
import { SidebarComponent} from "../../components/layout/sidebar/sidebar.component";
import {SharedLayoutComponent} from "../shared-layout/shared-layout.component";
export type MenuItems = {
  icon: string,
  label: string,
  route: string
}
@Component({
  selector: 'app-admin-layout',
  standalone: true,
  imports: [
    MatIcon,
    MatIconButton,
    MatSidenav,
    MatSidenavContainer,
    MatSidenavContent,
    MatToolbar,
    RouterOutlet,
    SidebarComponent,
    SharedLayoutComponent
  ],
  templateUrl: './admin-layout.component.html',
  styleUrl: './admin-layout.component.scss'
})
export class AdminLayoutComponent {
  menuItems = signal<MenuItems[]>(
    [
      {
        icon: "dashboard",
        label: "Dashborad",
        route: "dashboard"
      },
      {
        icon: "home",
        label: "Actuality",
        route: "actuality"
      },
      {
        icon: "receipt",
        label: "Study plan",
        route: "study-plan"
      },
      {
        icon: "dashboard",
        label: "Event",
        route: "dashboard1"
      }
    ]
  )
}
