import {Component, Input, signal} from '@angular/core';
import {AbstractControl, FormControl, ReactiveFormsModule} from "@angular/forms";
import {map, startWith} from "rxjs";
import {MatFormField} from "@angular/material/form-field";
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from "@angular/material/autocomplete";
import {MatInput} from "@angular/material/input";
import {AsyncPipe} from "@angular/common";

@Component({
  selector: 'app-auto-complete-form',
  standalone: true,
  imports: [
    MatFormField,
    MatAutocompleteTrigger,
    ReactiveFormsModule,
    MatInput,
    MatAutocomplete,
    AsyncPipe,
    MatOption
  ],
  templateUrl: './auto-complete-form.component.html',
  styleUrl: './auto-complete-form.component.scss'
})
export class AutoCompleteFormComponent<T> {
  options = signal<T[]>([])
  filteredOptions = signal<T[]>(this.options())
  @Input() formCtName!: string;
  @Input() fieldControl!: FormControl ;
  @Input() displayFn!: (option: any) => string;

  @Input()
  set optionsList(val: T[]) {
    this.options.set(val)
    this.filteredOptions.set(val)
  }

  ngOnInit() {
    this.fieldControl?.valueChanges.pipe(
      startWith(''),
      map(value => {
        const name = typeof value === 'string' ? value : this.displayFn(value);
        return name ? this._filter(name) : this.options().slice();
      })
    ).subscribe((dataFiltred) => {
      this.filteredOptions.set(dataFiltred)
    })
  }

  private _filter(name: string): T[] {
    const filterValue = name.toLowerCase();
    return this.options().filter(option => this.displayFn(option).toLowerCase().includes(filterValue));
  }

  @Input() name!: string

  getDisplayValue(): string {
    const rawValue = this.fieldControl?.getRawValue();

    if (rawValue && typeof rawValue === 'object' && this.name in
      rawValue
    ) {
      return (rawValue)

        ?.
        [this.name];
    }

    return rawValue as string;
  }


// private _filter(name: string): User[] {
//   const filterValue = name.toLowerCase();
//
//   return this.options.filter(option => option.name.toLowerCase().includes(filterValue));
// }

  /* end start autocoomlute module type*/

  prevent($event: Event) {

    $event.preventDefault()
    $event.stopPropagation()
  }


}
