import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {BASE_PATH} from "../../Constants";
import {Observable, Subject} from "rxjs";
import {ResponseModel} from "../../model/Response.model";
import {PageResponseModel} from "../../model/PageResponse.model";
import {FilterLevelParamModel, LevelDetailsResponseModel} from "./model/LevelDetailsResponse.model";

@Injectable({
  providedIn: 'root'
})
export class LevelService {

  _http: HttpClient = inject(HttpClient)
  _path: string = BASE_PATH + "levels/details"
  filterParam: FilterLevelParamModel = {};
  paramSubject = new Subject<FilterLevelParamModel>()


  getAllLevelWithDetails(page?: number | undefined, size?: number | undefined)
    : Observable<ResponseModel<PageResponseModel<LevelDetailsResponseModel>>> {
    let queryParams = new HttpParams();

    if (page !== undefined && size !== undefined) {
      queryParams = queryParams.set('page', page.toString());
      queryParams = queryParams.set('size', size.toString());
    }
    Object.keys(this.filterParam).forEach(key => {
      if (this.filterParam[key]) {
        queryParams = queryParams.set(key, this.filterParam[key] as string);
      }
    });

    queryParams.keys().forEach(key => {
      console.log(`parameter : ${key}: ${queryParams.get(key)}`);
    });

    return this._http.get<ResponseModel<PageResponseModel<LevelDetailsResponseModel>>>(this._path, {
      params: queryParams
    })

  }
}
