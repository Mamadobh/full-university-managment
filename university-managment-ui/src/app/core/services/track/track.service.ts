import {inject, Injectable} from '@angular/core';
import {BASE_PATH} from "../../Constants";
import {Observable} from "rxjs";
import {ResponseModel} from "../../model/Response.model";
import {PageResponseModel} from "../../model/PageResponse.model";
import {DepartmentResponseModel} from "../department/model/DepartmentResponse.model";
import {HttpClient, HttpParams} from "@angular/common/http";
import {TrackResponseModel} from "./model/TrackResponse.model";

@Injectable({
  providedIn: 'root'
})
export class TrackService {
  _http: HttpClient = inject(HttpClient)
  _path: string = BASE_PATH + "tracks"

  getAllTracks(page?: number | undefined, size?: number | undefined)
    : Observable<ResponseModel<PageResponseModel<TrackResponseModel>>> {
    let queryParams = new HttpParams();

    if (page !== undefined && size !== undefined) {
      queryParams = queryParams.set('page', page.toString());
      queryParams = queryParams.set('size', size.toString());
    }

    return this._http.get<ResponseModel<PageResponseModel<TrackResponseModel>>>(this._path, {
      params: queryParams
    })

  }


}
