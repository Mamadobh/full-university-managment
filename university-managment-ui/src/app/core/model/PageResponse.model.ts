export interface PageResponse<T> {
  content: T[]
  number: number
  size: number
  totalElments: number
  totalPages: number,
  first: boolean
  last: boolean
  record_from: number
  record_to: number
}
