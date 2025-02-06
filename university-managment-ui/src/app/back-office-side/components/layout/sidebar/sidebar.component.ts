import {Component, computed, Input, signal} from '@angular/core';
import {MatListItem, MatListItemIcon, MatListItemTitle, MatNavList} from "@angular/material/list";
import {MatIcon} from "@angular/material/icon";

import {JsonPipe, NgClass} from "@angular/common";
import {RouterLink, RouterLinkActive} from "@angular/router";
import {MenuItems} from "../../../../layout/admin-layout/admin-layout.component";


export type Size = { height: string, width: string }

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [
    MatNavList,
    MatListItem,
    MatIcon,
    MatListItemTitle, JsonPipe, MatListItemIcon, RouterLink, RouterLinkActive, NgClass
  ],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})

export class SidebarComponent {
  sidebarCollapsed = signal(false)
  @Input()
  set collapsed(val: boolean) {
    this.sidebarCollapsed.set(val)
  }

  menuItems = signal<MenuItems[]>([])
  @Input()
  set sidebarMenu(val: MenuItems[]) {
    this.menuItems.set(val)
  }

  logoSize = computed<Size>(
    () => this.sidebarCollapsed() ? {height: "45", width: "70"} :{height: "96", width: "160"}
  )


}
