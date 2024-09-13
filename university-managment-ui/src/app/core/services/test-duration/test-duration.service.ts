import {Injectable} from '@angular/core';
import {Subject} from "rxjs";
import {PageResponseModel} from "../../model/PageResponse.model";
import {CrudServiceService} from "../generic/crud-service.service";
import {TestDurationRequest, TestDurationResponse} from "./model/test-duration.model";
import {HttpClient} from "@angular/common/http";
import {BASE_PATH} from "../../Constants";

@Injectable({
  providedIn: 'root'
})
export class TestDurationService extends CrudServiceService<TestDurationRequest, TestDurationResponse> {
  allTestDurationReponse = new Subject<PageResponseModel<TestDurationResponse>>()

  constructor(http: HttpClient) {
    super(http, BASE_PATH + "test-durations")
  }

  findAllTestDuration(page?: number | undefined, size?: number | undefined) {
    this.getAll(page, size).subscribe({
      next: (res) => {
        this.allTestDurationReponse.next(res?.data)
      }
    })
  }
}
