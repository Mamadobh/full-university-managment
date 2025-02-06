import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {BASE_ADMIN_PATH, BASE_PATH} from "../../Constants";
import {Observable, Subject} from "rxjs";
import {DepartmentResponseModel} from "./model/DepartmentResponse.model";
import {PageResponseModel} from "../../model/PageResponse.model";
import {ResponseModel} from "../../model/Response.model";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  _http: HttpClient = inject(HttpClient)
  _path: string = BASE_ADMIN_PATH + "departments"
  allDepartmentReponse = new Subject<PageResponseModel<DepartmentResponseModel>>()

  constructor() {

  }

  findDepartment(page?: number | undefined, size?: number | undefined) {
    this.getAllDepartments().subscribe({
      next: (res) => {
        this.allDepartmentReponse.next(res?.data)
      }
    })
  }

  getAllDepartments(page?: number | undefined, size?: number | undefined)
    : Observable<ResponseModel<PageResponseModel<DepartmentResponseModel>>> {
    let queryParams = new HttpParams();

    if (page !== undefined && size !== undefined) {
      queryParams = queryParams.set('page', page.toString());
      queryParams = queryParams.set('size', size.toString());
    }

    return this._http.get<ResponseModel<PageResponseModel<DepartmentResponseModel>>>(this._path, {
      params: queryParams
    })

  }


}
