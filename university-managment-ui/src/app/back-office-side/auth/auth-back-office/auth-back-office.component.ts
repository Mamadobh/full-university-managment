import {Component, OnInit, signal} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatError, MatFormField, MatSuffix} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatFabButton, MatIconButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-auth-back-office',
  standalone: true,
  imports: [
    FormsModule,
    MatFormField,
    MatInput,
    ReactiveFormsModule,
    MatFabButton,
    MatIcon,
    MatIconButton,
    MatSuffix,
    MatError,
    NgIf
  ],
  templateUrl: './auth-back-office.component.html',
  styleUrl: './auth-back-office.component.scss',
})
export class AuthBackOfficeComponent implements OnInit{
  passwordf:string=""
  emailf:string=""

  hide = signal(true);
    ngOnInit(): void {
    }

  togglePassword($event: MouseEvent) {
    this.hide.set(!this.hide())
    event?.stopPropagation();
  }
}
