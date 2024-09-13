import {Injectable} from '@angular/core';
import {Subject} from "rxjs";
import {PageResponseModel} from "../../model/PageResponse.model";
import {HttpClient} from "@angular/common/http";
import {BASE_PATH} from "../../Constants";
import {TypeOfSubjectRequest, TypeOfSubjectResponse} from "./model/type-of-subject.model";
import {CrudServiceService} from "../generic/crud-service.service";

@Injectable({
  providedIn: 'root'
})
export class TypeOfSubjectService extends CrudServiceService<TypeOfSubjectRequest, TypeOfSubjectResponse> {

  alltTypeOfSubjectReponse = new Subject<PageResponseModel<TypeOfSubjectResponse>>()

  constructor(http: HttpClient) {
    super(http, BASE_PATH + "types")
  }

  findAllTestOfSubject(page?: number | undefined, size?: number | undefined) {
    this.getAll(page, size).subscribe({
      next: (res) => {
        this.alltTypeOfSubjectReponse.next(res?.data)
      }
    })
  }
}
