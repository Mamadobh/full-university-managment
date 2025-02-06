import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BASE_ADMIN_PATH, BASE_PATH} from "../../../Constants";
import {ResponseModel} from "../../../model/Response.model";
import {AdminAuthenticationResponse, AuthenticationRequest} from "../model/adminAuthentication.model";

@Injectable({
  providedIn: 'root'
})
export class AdminAuthService {

  constructor(private _http: HttpClient) {
  }
  public adminAuthenticate(request:AuthenticationRequest) {
    return this._http.post<ResponseModel<AdminAuthenticationResponse>>(BASE_PATH + "auth/back-office/authenticate" ,request )
  }

}
