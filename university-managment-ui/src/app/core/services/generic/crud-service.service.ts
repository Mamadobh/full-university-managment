import {Inject, Injectable, InjectionToken} from '@angular/core';
import {ResponseModel} from "../../model/Response.model";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {PageResponseModel} from "../../model/PageResponse.model";

export const ENTITY_PATH = new InjectionToken<string>('ENTITY_PATH');

@Injectable({
  providedIn: 'root'
})
export class CrudServiceService<Request, Response> {


  constructor(protected _http: HttpClient, @Inject(ENTITY_PATH) protected _path: string) {

  }


  getAll(page?: number | undefined, size?: number | undefined):
    Observable<ResponseModel<PageResponseModel<Response>>> {
    let queryParams = new HttpParams();

    if (page !== undefined && size !== undefined) {
      queryParams = queryParams.set('page', page.toString());
      queryParams = queryParams.set('size', size.toString());
    }
    return this._http.get<ResponseModel<PageResponseModel<Response>>>(this._path, {
      params: queryParams
    })
  }

  create(req: Request) {
    return this._http.post<number>(this._path, req)
  }

  get(id: number) {
    return this._http.get<ResponseModel<Response>>(this._path + "/" + id)
  }


}
