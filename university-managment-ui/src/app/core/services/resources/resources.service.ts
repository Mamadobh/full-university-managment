import {Injectable, signal} from '@angular/core';
import {CrudServiceService} from "../generic/crud-service.service";
import {ResourcesRequest, ResourcesResponse} from "./model/resource.model";
import {HttpClient} from "@angular/common/http";
import {BASE_ADMIN_PATH} from "../../Constants";
import {RoleResponse} from "../roles/model/roles.model";
import {ResourceRow} from "./model/resource.model";

@Injectable({
  providedIn: 'root'
})
export class ResourcesService extends CrudServiceService<ResourcesRequest, ResourcesResponse> {
  resourcesResponse = signal<ResourcesResponse[]>([]);

  constructor(http: HttpClient) {
    super(http, BASE_ADMIN_PATH + "resources")
  }

  mapperOfData(resources: ResourcesResponse[], roles: RoleResponse[]): ResourceRow[] {
    const res: ResourceRow[] = []
    resources.forEach((resource: ResourcesResponse) => {
      const rowResource: ResourceRow = {
        resource: resource.name,
        level: 0,
        id: resource.id,
        permissions: [],
        ...Object.fromEntries(roles.map(role => [role.name, false]))
      }
      const permissions: ResourceRow[] = []
      const PermissionNumberForEachRole: number[] = Array(roles.length).fill(0)
      resource.permissions.forEach((permission) => {
        let rowPermission = {
          resource: permission.name.split("_").map(el => el.toString().toLowerCase()).join(" "),
          level: 1,
          id: permission.id,
          ...Object.fromEntries(roles.map(role => [role.name, false]))
        }
        roles.forEach((role, index) => {
          if (role.permissions.some(perm => perm.id == permission.id)) {
            rowPermission[role.name] = true
            PermissionNumberForEachRole[index]++
          } else {
            rowPermission[role.name] = false
          }
        })

        permissions.push(rowPermission);
      })
      roles.forEach((role, index) => {
        rowResource[role.name] = PermissionNumberForEachRole[index] == resource.permissions.length
      })
      rowResource.permissions = permissions;
      res.push(rowResource)
    })

    return res;
  }

}
