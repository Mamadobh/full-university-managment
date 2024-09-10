import {first, last} from "rxjs";

export class PageResponseModel<T> {
  content: T[] = []
  number: number = 0
  size: number = 10
  totalElments: number = 0
  totalPages: number = 0
  first: boolean = false
  last: boolean = false
  record_from?: number
  record_to?: number

  constructor() {
  }

}
