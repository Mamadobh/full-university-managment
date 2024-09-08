import {Component, EventEmitter, Input, Output} from '@angular/core';
import {AsyncPipe} from "@angular/common";
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from "@angular/material/autocomplete";
import {MatFormField} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatInput} from "@angular/material/input";
import {AbstractControl, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {map, Observable, startWith} from "rxjs";
import {User} from "../../../pages/study-plan-pages/study-plan-create/study-plan-create.component";

@Component({
  selector: 'app-test-form',
  standalone: true,
  imports: [
    AsyncPipe,
    MatAutocomplete,
    MatAutocompleteTrigger,
    MatFormField,
    MatIcon,
    MatInput,
    MatOption,
    ReactiveFormsModule
  ],
  templateUrl: './test-form.component.html',
  styleUrl: './test-form.component.scss'
})
export class TestFormComponent {
  myControl = new FormControl<string | User>('');
  options: User[] = [{name: 'Mary'}, {name: 'Shelley'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}];
  filteredOptions!: Observable<User[]>;
  _formTest!: FormGroup;
  @Input()
  set formTest(value: AbstractControl) {
    this._formTest = value as FormGroup;
  }

  @Input() testIndex!: number;
  @Output()
  removeTest = new EventEmitter<number>()
  @Output()
  addTest = new EventEmitter<number>()

  removeTestEmit(index: number) {
    this.removeTest.emit(index)
  }

addTestEmit(index: number) {
    this.addTest.emit(index)
  }


  ngOnInit() {
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => {
        const name = typeof value === 'string' ? value : value?.name;
        return name ? this._filter(name as string) : this.options.slice();
      }),
    );
  }

  getDisplayValue(): string {
    const rawValue = this.myControl.getRawValue();
    if (rawValue && typeof rawValue === 'object' && 'name' in rawValue) {
      return (rawValue as User).name;
    }
    return rawValue as string;
  }

  displayFn(user: User): string {
    return user && user.name ? user.name : '';
  }

  private _filter(name: string): User[] {
    const filterValue = name.toLowerCase();

    return this.options.filter(option => option.name.toLowerCase().includes(filterValue));
  }

  /* end start autocoomlute module type*/

  prevent($event: Event) {

    $event.preventDefault()
    $event.stopPropagation()
  }
}
