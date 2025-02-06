import { Component } from '@angular/core';
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-actuality',
  standalone: true,
  imports: [
    MatFormField,
    MatInput,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './actuality.component.html',
  styleUrl: './actuality.component.scss'
})
export class ActualityComponent {

}
