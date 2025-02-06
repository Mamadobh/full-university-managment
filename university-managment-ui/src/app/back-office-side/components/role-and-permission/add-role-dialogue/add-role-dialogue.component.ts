import {Component, inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";
import {MatError, MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {FormsModule, NgForm, ReactiveFormsModule} from "@angular/forms";
import {JsonPipe, NgIf} from "@angular/common";
import {RolesService} from "../../../../core/services/roles/roles.service";
import {RoleRequest} from "../../../../core/services/roles/model/roles.model";

@Component({
  selector: 'app-add-role-dialogue',
  standalone: true,
  imports: [
    MatButtonModule,
    MatIcon,
    MatError,
    MatFormField,
    MatInput,
    ReactiveFormsModule,
    FormsModule,
    NgIf,
    JsonPipe,

  ],
  templateUrl: './add-role-dialogue.component.html',
  styleUrl: './add-role-dialogue.component.scss'
})
export class AddRoleDialogueComponent {
  dialogRef: MatDialogRef<AddRoleDialogueComponent> = inject(MatDialogRef<AddRoleDialogueComponent>)
  data: any = inject(MAT_DIALOG_DATA);
  roleSerive = inject(RolesService)
  fieldRole = ""
  msgError = ""

  onCancel(): void {
    this.dialogRef.close(false);
  }

  onConfirm(): void {
    this.dialogRef.close(true)
  }

  ngOnInit() {
    console.log("this.data.fieldRole; ", this.data.fieldRole)
    this.fieldRole = this.data.role.name;
  }

  submit(form: NgForm) {
    if (!form.valid) {
      this.markFormGroupTouched(form);
    } else {
      if (!this.data.role.name) {
        this.addRole();
      } else {
        console.log("update")
        this.updateRole()
      }

    }
  }

  private addRole() {
    this.roleSerive.create(new RoleRequest(this.fieldRole)).subscribe({
      next: (res) => {
        this.onConfirm()
      },
      error: (err) => {
        console.log("error  ", err)
        if (err.error.error.errors) {
          this.msgError = err.error.error.errors.name
        } else {
          this.msgError = err.error.error
        }

      }
    })
  }

  private markFormGroupTouched(form: NgForm) {
    Object.keys(form.controls).forEach((controlName) => {
      const control = form.controls[controlName];
      control.markAsTouched();
    });
  }

  private updateRole() {
    this.roleSerive.update(new RoleRequest(this.fieldRole, this.data.role.id))
      .subscribe({
        next: (res) => {
          this.onConfirm()
        },
        error: (err) => {
          this.error(err)
        }
      })
  }

  error(error) {
    console.log("error  ", error)
    if (error.error.error.errors) {
      this.msgError = error.error.error.errors.name
    } else {
      this.msgError = error.error.error
    }
  }

}
