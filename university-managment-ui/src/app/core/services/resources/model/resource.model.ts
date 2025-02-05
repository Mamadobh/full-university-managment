export class ResourcesResponse {
  constructor(public name: string, public permissions: PermissionResponse[],public  id: number) {
  }

}

export class PermissionResponse {
  constructor(public name: string, public id: number) {
  }
}

export class PermissionRequest {
  constructor(public name: string, public id?: number,) {
  }
}

export class ResourcesRequest {
  constructor(public name: string, public permissions: PermissionRequest[], public id?: number) {

  }
}

export type ResourceRow = {
  resource: string;
  id:number,
  level: number;
  permissions: ResourceRow[];
} & {
  [key: string]: boolean | number | string | ResourceRow[];
};

export type ConfirmDialogContext={
  permissions:string[],
  role:string
  title:string
}
