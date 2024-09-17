import {AbstractControl, FormArray, FormGroup, ValidatorFn} from "@angular/forms";
import * as _moment from 'moment';
import {default as _rollupMoment} from 'moment';
import {type} from "node:os";

const moment = _rollupMoment || _moment;

export function ValidateDate(group: AbstractControl) {
  const startDate = moment(group.get("startDate")?.value)
  const endDate = moment(group.get("endDate")?.value)
  startDate.year(2000);
  endDate.year(2000)
  if (endDate.isAfter(startDate)) {
    return null
  }
  group.get("startDate")?.setErrors({invalidDate: true})
  group.get("endDate")?.setErrors({invalidDate: true})
  return {invalidDate: true}

}

export function validateDuplication(controlName: string): ValidatorFn {
  return (control: AbstractControl): { [key: string]: boolean } | null => {

    if (!(control instanceof FormArray)) {
      return null;
    }

    const formArray = control.value


    if (!formArray || formArray.length == 0) {
      return null;
    }
    const values = control
      .controls
      .map((el: AbstractControl) => {
        return typeof el.get(controlName)?.value === "string" ?
          el.get(controlName)?.value?.toLowerCase() :
          el.get(controlName)?.value?.[controlName]?.toLowerCase();
      })
      .filter(Boolean);


    const duplicates = values
      .map((value: any, index: any, self: any) => self.indexOf(value) !== index ? value : null)
      .filter(Boolean);
    if (duplicates.length > 0) {
      control.controls.forEach((group: AbstractControl) => {
        const field = group.get(controlName);
        const value = typeof field?.value === "string" ? field?.value?.toLowerCase() : field?.value?.[controlName]?.toLowerCase()

        if (duplicates.includes(value)) {
          field?.setErrors({duplicate: true})

        } else if (field?.hasError("duplicate")) {
          field?.setErrors(null);
        }
      })

      return {
        duplicationData: true
      }
    } else {
      control.controls.forEach((group: AbstractControl) => {
        const field = group.get(controlName);
        if (field?.hasError("duplicate")) {
          field?.setErrors(null);
        }
      })
      return null;
    }
    return null

  }
}
