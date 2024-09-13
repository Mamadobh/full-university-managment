import {Injectable} from '@angular/core';
import {Subject} from "rxjs";
import {PageResponseModel} from "../../model/PageResponse.model";
import {CoefficientRequest, CoefficientResponse} from "./model/coefficient.model";
import {CrudServiceService} from "../generic/crud-service.service";
import {HttpClient} from "@angular/common/http";
import {BASE_PATH} from "../../Constants";

@Injectable({
  providedIn: 'root'
})
export class CoefficientService extends CrudServiceService<CoefficientRequest, CoefficientResponse> {

  allCoefficientReponse = new Subject<PageResponseModel<CoefficientResponse>>()

  constructor(http: HttpClient) {
    super(http, BASE_PATH + "coefficients")
  }

  findAllCoefficient(page?: number | undefined, size?: number | undefined) {
    this.getAll(page, size).subscribe({
      next: (res) => {
        this.allCoefficientReponse.next(res?.data)
      }
    })
  }


}
