import {Component, EventEmitter, Output, signal} from '@angular/core';
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatToolbar} from "@angular/material/toolbar";
import {MatBadge} from "@angular/material/badge";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    MatIcon,
    MatIconButton,
    MatToolbar,
    MatBadge
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  collapsed = signal(false);

  @Output()
  collapsedChange = new EventEmitter<boolean>();
  toggle() {
    this.collapsed.set(!this.collapsed());
    this.collapsedChange.emit(this.collapsed());

  }
}
