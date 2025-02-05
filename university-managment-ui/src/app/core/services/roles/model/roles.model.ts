import {PermissionResponse} from "../../resources/model/resource.model";

export class RoleRequest {
  constructor(public name, public id?: number) {
  }
}

export class RoleResponse {
  constructor(public id: number, public name, public permissions: PermissionResponse[]) {
  }

}

export class ManagePermissionRequest {
  constructor(public roleId: number, public permissions: number[]) {
  }

}

