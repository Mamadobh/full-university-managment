import {Component, EventEmitter, Input, Output} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {User} from "../../../pages/study-plan-pages/study-plan-create/study-plan-create.component";
import {map, Observable, startWith} from "rxjs";
import {MatFormField} from "@angular/material/form-field";
import {MatAutocomplete, MatAutocompleteTrigger, MatOption} from "@angular/material/autocomplete";
import {AsyncPipe} from "@angular/common";
import {MatIcon} from "@angular/material/icon";
import {MatInput} from "@angular/material/input";

@Component({
  selector: 'app-subject-type-form',
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
  templateUrl: './subject-type-form.component.html',
  styleUrl: './subject-type-form.component.scss'
})
export class SubjectTypeFormComponent {
  myControl = new FormControl<string | User>('');
  options: User[] = [{name: 'Mary'}, {name: 'Shelley'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}, {name: 'Igor'}];
  filteredOptions!: Observable<User[]>;
  _formSubjectType!: FormGroup;
  @Input()
  set formSubjectType(value: AbstractControl) {
    this._formSubjectType = value as FormGroup;
  }
  @Input() subjectTypeIndex!: number;

  @Output()
  removeSubjectType = new EventEmitter<number>()
  @Output()
  addSubjectType = new EventEmitter<number>()

  removeSubjectTypeEmit(index: number) {
    this.removeSubjectType.emit(index)
  }

  addSubjectTypeEmit(index: number) {
    this.addSubjectType.emit(index)
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
