import {Component, inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {NgTemplateOutlet} from "@angular/common";
import {MatIcon} from "@angular/material/icon";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'app-confirm-dialogue',
  standalone: true,
  imports: [
    NgTemplateOutlet,
    MatIcon,
    MatButton
  ],
  templateUrl: './confirm-dialogue.component.html',
  styleUrl: './confirm-dialogue.component.scss'
})
export class ConfirmDialogueComponent {
  dialogRef: MatDialogRef<ConfirmDialogueComponent> = inject(MatDialogRef<ConfirmDialogueComponent>)
  data: any = inject(MAT_DIALOG_DATA);

  onCancel(): void {
    this.dialogRef.close(false);
  }

  onConfirm(): void {
    this.dialogRef.close(true)
  }

}
