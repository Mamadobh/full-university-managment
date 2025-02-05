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
    if (typeof window !== 'undefined' && window.localStorage) {
      this.tokenService.token = "eyJhbGciOiJIUzM4NCJ9.eyJmdWxsTmFtZSI6Im1vaGFtZWRBZG1pbiBiZW4gSGFmc2lhIiwic3ViIjoiYWRtaW5AZ21haWwuY29tIiwiaWF0IjoxNzM4NzQ3MTAwLCJleHAiOjE3Mzg4MzM1MDAsImF1dGhvcml0aWVzIjpbIkFETUlOIl19.XEhZnczkJNKsqP-dK_y22a-as58ssOUiH8DW_i26i8CA3y1VlJA6CU7I5qTwSNga"
    }

  }

}
