import {PermissionResponse} from "../../resources/model/resource.model";

export class RoleRequest {
  constructor(public name:string, public id?: number) {
  }
}

export class RoleResponse {
  constructor(public id: number, public name:string, public permissions: PermissionResponse[]) {
  }

}

export class ManagePermissionRequest {
  constructor(public roleId: number, public permissions: number[]) {
  }

}

