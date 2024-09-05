import {Component, inject, Input, OnInit, signal, ViewEncapsulation} from '@angular/core';
import {AbstractControl, FormControl, ReactiveFormsModule} from "@angular/forms";
import {map, startWith} from "rxjs";
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from "@angular/material/autocomplete";
import {MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatInput} from "@angular/material/input";
import {LevelService} from "../../../core/services/level/level.service";

@Component({
  selector: 'app-autocomplete',
  standalone: true,
  imports: [
    MatAutocomplete,
    MatOption,
    ReactiveFormsModule,
    MatLabel,
    MatFormField,
    MatIcon,
    MatInput,
    MatSuffix,
    MatAutocompleteTrigger,

  ],
  templateUrl: './autocomplete.component.html',
  styleUrl: './autocomplete.component.scss',
  encapsulation: ViewEncapsulation.None


})
export class AutocompleteComponent<T> implements OnInit {

  levelService: LevelService = inject(LevelService);
  @Input() label!: string;
  @Input() fieldControl!: FormControl<string | T | null>;
  @Input() displayFn!: (option: any) => string;

  @Input()
  set optionsList(val: T[]) {
    this.options.set(val)
    this.filteredOptions.set(val)
  }

  options = signal<T[]>([])
  filteredOptions = signal<T[]>(this.options())

  ngOnInit() {
    this.fieldControl.valueChanges.pipe(
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

  onblur() {

    switch (this.label) {
      case "Department":
        this.filterAndEmit(this.fieldControl, "departmentLike");
        break;
      case "Track":
        this.filterAndEmit(this.fieldControl, "trackLike");
        break;
      case "Speciality":
        this.filterAndEmit(this.fieldControl, "specialityLike");
        break;
      default:
        break;
    }
  }

  filterAndEmit(fieldControl: AbstractControl, filterKey: string) {
    const fieldValue = typeof fieldControl.getRawValue() === "string" ?
      fieldControl.getRawValue() :
      fieldControl.getRawValue()?.name;

    console.log("calle of the onblur function from ", this.label)
    console.log("FiltredOptions  ", this.filteredOptions())
    console.log("Options   ", this.options())
    console.log("FieldValue   ", fieldValue)
    console.log("param key value  ", this.levelService.filterParam)

    console.log("=================================================================== ", this.filteredOptions().length !== 0 && (fieldValue || this.levelService.filterParam[filterKey]))
    if (fieldValue) {
      console.log("the field value give true ", fieldValue)
    }
    if (this.levelService.filterParam[filterKey]) {
      console.log("this.levelService.filterParam[filterKey]) givr true ", this.levelService.filterParam[filterKey])

    }
    if (this.filteredOptions().length !== 0 && (fieldValue || this.levelService.filterParam[filterKey])) {

      console.log("emit value")
      this.levelService.paramSubject.next({
        ...this.levelService.filterParam,
        [filterKey]: fieldValue
      });
    }
  }

}
