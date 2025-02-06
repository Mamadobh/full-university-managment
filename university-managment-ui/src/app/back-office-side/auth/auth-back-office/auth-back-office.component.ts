import {Component, inject, signal, Type} from '@angular/core';
import {MatFabButton, MatIconButton} from "@angular/material/button";
import {MatFormField, MatSuffix} from "@angular/material/form-field";
import {FormsModule, NgForm} from "@angular/forms";
import {MatIcon} from "@angular/material/icon";
import {MatInput} from "@angular/material/input";
import {JsonPipe, NgIf} from "@angular/common";
import {AuthenticationRequest} from "../../../core/services/auth/model/adminAuthentication.model";
import {AdminAuthService} from "../../../core/services/auth/admin-auth/admin-auth.service";
import {Router} from "@angular/router";
import {TokenService} from "../../../core/services/token/token.service";

@Component({
  selector: 'app-auth-back-office',
  standalone: true,
  imports: [
    MatFabButton,
    MatFormField,
    FormsModule,
    MatIcon,
    MatInput,
    NgIf,
    MatIconButton,
    MatSuffix,
    JsonPipe
  ],
  templateUrl: './auth-back-office.component.html',
  styleUrl: './auth-back-office.component.scss'
})
export class AuthBackOfficeComponent {
  passwordValue: string = ""
  emailValue: string = ""
  adminAuthService: AdminAuthService = inject(AdminAuthService)
  private router: Router = inject(Router)
  private tokenService: TokenService = inject(TokenService)

  errorsMessage: Map<string, string> = new Map<string, string>();
  errorMessage: string = ""

  hide = signal(true);

  ngOnInit(): void {
  }

  togglePassword($event: MouseEvent) {
    this.hide.set(!this.hide())
    event?.stopPropagation();
  }

  private authenticate(request: AuthenticationRequest) {
    this.adminAuthService.adminAuthenticate(request).subscribe({
      next: (res) => {
        console.log("response ", res.data)
        if (typeof window !== 'undefined' && window.localStorage) {
          this.tokenService.token = res.data.token
          this.router.navigate(["back-office", "views", "dashboard"])
          this.errorsMessage = new Map<string, string>()
          this.errorMessage = "";

        }

      },
      error: (err) => {
        console.log("error ", err.error)

        if (err?.error?.error?.errors) {
          this.errorsMessage = new Map<string, string>(Object.entries(err?.error?.error?.errors))
        }
      }
    })
  }


  onSubmit(formLogin: NgForm) {
    if (formLogin.valid) {
      this.authenticate(new AuthenticationRequest(this.passwordValue, this.emailValue))
      console.log("form auth submited");
    }
  }

  getPasswordError(): string {
    return this.errorsMessage?.get('password') ?? 'Password is required !!';
  }

  getEmailError(): string {
    return this.errorsMessage?.get('email') ?? 'Email is required 11 !!';
  }
}
