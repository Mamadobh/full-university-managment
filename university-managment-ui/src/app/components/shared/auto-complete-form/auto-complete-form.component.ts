import {Component, ElementRef, EventEmitter, Input, OnInit, Output, signal, ViewChild} from '@angular/core';
import {FormControl, ReactiveFormsModule} from "@angular/forms";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption} from "@angular/material/autocomplete";
import {MatInput} from "@angular/material/input";
import {MatSelect} from "@angular/material/select";
import {MatIcon} from "@angular/material/icon";
import {map, startWith} from "rxjs";

@Component({
  selector: ' app-auto-complete-form',
  standalone: true,
  imports: [
    MatFormField,
    ReactiveFormsModule,
    MatInput,
    MatOption,
    MatSelect,
    MatLabel,
    MatIcon
  ],
  templateUrl: './auto-complete-form.component.html',
  styleUrls: ['./auto-complete-form.component.scss']
})
export class AutoCompleteFormComponent<T> implements OnInit {

  options = signal<T[]>([])
  filteredOptions = signal<T[]>(this.options())
  @Input() fieldControl!: FormControl;
  @Input() displayFn!: (option: any) => string;
  @Output() createItem = new EventEmitter();
  @Input() label!: string
  @Input() showSearchLength: number = 1;
  @Input() hideSearch: boolean = false;
  @Input() numberType: boolean = false


  @Input()
  set optionsList(val: T[]) {
    this.options.set(val)
    const value = this.searchControl.getRawValue()
    this.filteredOptions.set(value ? this._filter(value) : this.options().slice())
  }

  searchControl = new FormControl<string>("")
  @ViewChild('searchInput', {static: false}) searchInput!: ElementRef<HTMLInputElement>;

  onSelectOpened(isOpen: boolean): void {
    this.searchControl.setValue("")

    if (isOpen && this.searchInput) {
      this.searchInput.nativeElement.focus();
    }
  }


  ngOnInit() {
    this.searchControl?.valueChanges.pipe(
      startWith(''),
      map(value => {

        let name = typeof value === 'string' ? value : this.displayFn(value);

        if (isNaN(+name) && this.numberType) {
          name = value?.replace(/[^0-9]/g, '') as string;
          this.searchControl.setValue(name, {emitEvent: false});
        }


        return name ? this._filter(name) : this.options().slice();
      })
    ).subscribe((dataFiltred) => {
      this.filteredOptions.set(dataFiltred)
    })
  }

  getId(option: any): string {
    if (option && "id" in option) {
      return option["id"] as string
    }
    return new Date().getMilliseconds().toString()
  }

  private _filter(name: string): T[] {
    const filterValue = name.toLowerCase();
    return this.options().filter(option => this.displayFn(option).toLowerCase().startsWith(filterValue));
  }

  compareItem = (o1: any, o2: any) => o1 && o2 && o1.id === o2.id;


  addItem() {
    this.createItem.emit(this.searchControl.getRawValue())
  }


}
