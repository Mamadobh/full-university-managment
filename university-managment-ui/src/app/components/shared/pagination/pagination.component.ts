import {Component, EventEmitter, Input, Output, ViewEncapsulation} from '@angular/core';
import {MatIcon} from "@angular/material/icon";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";

@Component({
  selector: 'app-pagination',
  standalone: true,
  imports: [
    MatIcon,
    MatFormField,
    MatLabel,
    MatSelect,
    MatOption,
  ],
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.scss',
  encapsulation: ViewEncapsulation.None

})
export class PaginationComponent {
  @Input() totalPage!: number
  @Input() totalElment!: number
  @Input() isLast!: boolean
  @Input() isFirst!: boolean
  @Input() pageSize: number = 10
  @Input() currentPage: number = 0
  @Input() pageInterval: { start: number, end: number } = {
    start: 1, end: 3
  }


  @Output()
  nextPage = new EventEmitter()
  @Output()
  previousPage = new EventEmitter()
  @Output()
  targetPage = new EventEmitter()
  @Output()
  pageSizeChange = new EventEmitter()

  goToNextPage() {
    if (!this.isLast) {
      this.nextPage.emit(++this.currentPage)
    }
  }

  goToPreviousPage() {
    if (!this.isFirst) {
      this.previousPage.emit(--this.currentPage)
    }
  }

  goToTargetPage(target: number) {

    if (target <= this.totalPage) {
      this.targetPage.emit(--target)
    }
  }

  changePageSize(size: number) {
    this.pageSizeChange.emit(size)

  }


}
