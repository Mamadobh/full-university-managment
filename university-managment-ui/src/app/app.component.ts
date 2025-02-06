import {AfterViewInit, Component, inject} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {AdminLayoutComponent} from "./layout/admin-layout/admin-layout.component";
import {TokenService} from "./core/services/token/token.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    AdminLayoutComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements AfterViewInit {
  title = 'university-managment-ui';
  tokenService = inject(TokenService)

  constructor() {

  }

  ngAfterViewInit(): void {

  }

}
