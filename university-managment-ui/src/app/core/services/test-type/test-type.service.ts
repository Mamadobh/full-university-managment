import {Injectable} from '@angular/core';
import {CrudServiceService} from "../generic/crud-service.service";
import {TestTypeRequest, TestTypeResponse} from "./model/test-type.model";
import {Subject} from "rxjs";
import {PageResponseModel} from "../../model/PageResponse.model";
import {HttpClient} from "@angular/common/http";
import {BASE_ADMIN_PATH, BASE_PATH} from "../../Constants";

@Injectable({
  providedIn: 'root'
})
export class TestTypeService extends CrudServiceService<TestTypeRequest, TestTypeResponse> {
  allTestTypeReponse = new Subject<PageResponseModel<TestTypeResponse>>()

  constructor(http: HttpClient) {
    super(http, BASE_ADMIN_PATH + "test-types")
  }

  findAllTestType(page?: number | undefined, size?: number | undefined) {
    this.getAll(page, size).subscribe({
      next: (res) => {
        this.allTestTypeReponse.next(res?.data)
      }
    })
  }
}
