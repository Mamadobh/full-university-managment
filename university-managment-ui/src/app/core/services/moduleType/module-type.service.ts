import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {BASE_PATH} from "../../Constants";
import {Observable, Subject} from "rxjs";
import {PageResponseModel} from "../../model/PageResponse.model";
import {ResponseModel} from "../../model/Response.model";
import {ModuleTypeRequest, ModuleTypeResponse} from "./model/module-type.model";

@Injectable({
  providedIn: 'root'
})
export class ModuleTypeService {
  _http: HttpClient = inject(HttpClient)
  _path: string = BASE_PATH + "module-types"
  allModuleTypeReponse = new Subject<PageResponseModel<ModuleTypeResponse>>()

  findAllModuleTypes(page?: number | undefined, size?: number | undefined) {
    this.getAllModuleType(page, size).subscribe({
      next: (res) => {
        this.allModuleTypeReponse.next(res?.data)
      }
    })
  }

  getAllModuleType(page?: number | undefined, size?: number | undefined)
    : Observable<ResponseModel<PageResponseModel<ModuleTypeResponse>>> {
    let queryParams = new HttpParams();

    if (page !== undefined && size !== undefined) {
      queryParams = queryParams.set('page', page.toString());
      queryParams = queryParams.set('size', size.toString());
    }

    return this._http.get<ResponseModel<PageResponseModel<ModuleTypeResponse>>>(this._path, {
      params: queryParams
    })

  }


  addModuleType(moduleType: ModuleTypeRequest) {
    return this._http.post<ResponseModel<number>>(this._path, moduleType)
  }
}
