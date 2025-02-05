import {Injectable, signal} from '@angular/core';
import {CrudServiceService} from "../generic/crud-service.service";
import {ManagePermissionRequest, RoleRequest, RoleResponse} from "./model/roles.model";
import {HttpClient} from "@angular/common/http";
import {BASE_ADMIN_PATH, BASE_PATH} from "../../Constants";

@Injectable({
  providedIn: 'root'
})
export class RolesService extends CrudServiceService<RoleRequest, RoleResponse> {
  roles = signal<RoleResponse[]>([])

  constructor(http: HttpClient) {
    super(http, BASE_ADMIN_PATH + "roles")
  }

  addPermissionsToRole(req: ManagePermissionRequest) {
    return this._http.post<number>(this._path + "/add-role", req)
  }

  removePermissionsFromRole(req: ManagePermissionRequest) {
    return this._http.post<number>(this._path + "/remove-role", req)
  }

}
