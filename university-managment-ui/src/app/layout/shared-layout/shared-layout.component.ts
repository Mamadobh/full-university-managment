import {Component, computed, Input, signal} from '@angular/core';
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatSidenav, MatSidenavContainer, MatSidenavContent} from "@angular/material/sidenav";
import {MatToolbar} from "@angular/material/toolbar";
import {RouterOutlet} from "@angular/router";
import {SidebarComponent} from "../../components/layout/sidebar/sidebar.component";
import {MenuItems} from "../admin-layout/admin-layout.component";
import {HeaderComponent} from "../../components/layout/header/header.component";

@Component({
  selector: 'app-shared-layout',
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
    HeaderComponent
  ],
  templateUrl: './shared-layout.component.html',
  styleUrl: './shared-layout.component.scss'
})
export class SharedLayoutComponent {
  collapsed = signal(false);
  onCollapsedChange(collapsedValue: boolean) {
    this.collapsed.set(collapsedValue)
  }

  sideBarWidth = computed(() => this.collapsed() ? "112px" : "256px")

  menuItems = signal<MenuItems[]>([])
  @Input()
  set sidebarMenu(val: MenuItems[]) {
    this.menuItems.set(val)
  }
}
