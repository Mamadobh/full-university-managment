import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {BASE_ADMIN_PATH, BASE_PATH} from "../../Constants";
import {Observable, Subject} from "rxjs";
import {ResponseModel} from "../../model/Response.model";
import {PageResponseModel} from "../../model/PageResponse.model";
import {
  FilterLevelParamModel,
  LevelDetailsResponseModel,
  LevelRequest,
  LevelResponse
} from "./model/LevelDetailsResponse.model";
import {CrudServiceService} from "../generic/crud-service.service";

@Injectable({
  providedIn: 'root'
})
export class LevelService extends CrudServiceService<LevelRequest, LevelResponse> {
  resources:string[]=["level"]
  __path: string = BASE_ADMIN_PATH + "levels/details";
  filterParam: FilterLevelParamModel = {};
  paramSubject = new Subject<FilterLevelParamModel>();

  constructor() {
    super(inject(HttpClient), BASE_PATH + "levels"); // Corrected "tihs" to "this"
  }

getStudyPlanPdf(id:number):string{
return `${this._path}/${id}/study-plan-pdf`
}
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

    return this._http.get<ResponseModel<PageResponseModel<LevelDetailsResponseModel>>>(this.__path, {
      params: queryParams
    })

  }


}
