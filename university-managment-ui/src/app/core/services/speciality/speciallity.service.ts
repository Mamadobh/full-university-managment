import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {BASE_PATH} from "../../Constants";
import {Observable} from "rxjs";
import {ResponseModel} from "../../model/Response.model";
import {PageResponseModel} from "../../model/PageResponse.model";
import {TrackResponseModel} from "../track/model/TrackResponse.model";
import {SpecialityResponseModel} from "./model/SpecialityResponse.model";

@Injectable({
  providedIn: 'root'
})
export class SpeciallityService {
  _http: HttpClient = inject(HttpClient)
  _path: string = BASE_PATH + "specialities"

  getAllSpeciality(page?: number | undefined, size?: number | undefined)
    : Observable<ResponseModel<PageResponseModel<SpecialityResponseModel>>> {
    let queryParams = new HttpParams();

    if (page !== undefined && size !== undefined) {
      queryParams = queryParams.set('page', page.toString());
      queryParams = queryParams.set('size', size.toString());
    }

    return this._http.get<ResponseModel<PageResponseModel<SpecialityResponseModel>>>(this._path, {
      params: queryParams
    })

  }
}
