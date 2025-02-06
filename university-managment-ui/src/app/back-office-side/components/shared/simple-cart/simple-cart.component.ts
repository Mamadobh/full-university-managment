import {Component, Input} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-simple-cart',
  standalone: true,
  imports: [
    MatButton,
    RouterLink,
  ],
  templateUrl: './simple-cart.component.html',
  styleUrl: './simple-cart.component.scss'
})
export class SimpleCartComponent {

  @Input() level:string="";
  @Input() track_speciality:string=""
  @Input() department:string=""
  @Input() link:string=""
}
