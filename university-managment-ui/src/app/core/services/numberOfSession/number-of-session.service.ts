import { Injectable } from '@angular/core';
import {CrudServiceService} from "../generic/crud-service.service";
import {NumberOfSessionRequest, NumberOfSessionResponse} from "./model/number-of-session.model";
import {Subject} from "rxjs";
import {PageResponseModel} from "../../model/PageResponse.model";
import {HttpClient} from "@angular/common/http";
import {BASE_ADMIN_PATH, BASE_PATH} from "../../Constants";

@Injectable({
  providedIn: 'root'
})
export class NumberOfSessionService extends CrudServiceService<NumberOfSessionRequest, NumberOfSessionResponse> {

  allNumberOfSessionReponse = new Subject<PageResponseModel<NumberOfSessionResponse>>()

  constructor(http: HttpClient) {
    super(http, BASE_ADMIN_PATH + "session-numbers ")
  }

  findAllNumberOfSession(page?: number | undefined, size?: number | undefined) {
    this.getAll(page, size).subscribe({
      next: (res) => {
        this.allNumberOfSessionReponse.next(res?.data)
      }
    })
  }

}
