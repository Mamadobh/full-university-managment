import { Component } from '@angular/core';
import {RouterOutlet} from "@angular/router";
import {AdminLayoutComponent} from "../../layout/admin-layout/admin-layout.component";

@Component({
  selector: 'app-back-office',
  standalone: true,
  imports: [
    RouterOutlet,
    AdminLayoutComponent
  ],
  templateUrl: './back-office.component.html',
  styleUrl: './back-office.component.scss'
})
export class BackOfficeComponent {
}
