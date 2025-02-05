import {
  ChangeDetectionStrategy,
  Component,
  computed,
  inject,
  signal,
  TemplateRef,
  ViewChild,
  ViewEncapsulation
} from '@angular/core';
import {JsonPipe, NgIf} from "@angular/common";
import {MatTableModule} from "@angular/material/table";
import {MatTreeModule} from "@angular/material/tree";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatCheckbox, MatCheckboxChange} from "@angular/material/checkbox";
import {RolesService} from "../../../core/services/roles/roles.service";
import {ResourcesService} from "../../../core/services/resources/resources.service";
import {ConfirmDialogContext, ResourceRow} from "../../../core/services/resources/model/resource.model";
import {PageHeaderComponent} from "../../../components/shared/page-header/page-header.component";
import {Link} from "../../../core/model/utils";
import {BASE_ADMIN_ROUTE} from "../../../core/Constants";
import {
  MatList,
  MatListItem,
  MatListItemIcon,
  MatListItemTitle,
  MatListOption,
  MatSelectionList
} from "@angular/material/list";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDialogueComponent} from "../../components/confirm-dialogue/confirm-dialogue.component";
import {ManagePermissionRequest, RoleRequest} from "../../../core/services/roles/model/roles.model";
import {AddRoleDialogueComponent} from "../../components/add-role-dialogue/add-role-dialogue.component";
import {MatPrefix, MatSuffix} from "@angular/material/form-field";
import {MatMenu, MatMenuItem, MatMenuTrigger} from "@angular/material/menu";


@Component({
  selector: 'app-role-and-permissions',
  standalone: true,
  imports: [
    MatTableModule,
    MatTreeModule,
    MatIconModule,
    MatButtonModule,
    NgIf,
    MatCheckbox,
    JsonPipe,
    PageHeaderComponent,
    MatListItemTitle,
    MatSelectionList,
    MatListOption,
    ConfirmDialogueComponent,
    MatListItem,
    MatList,
    MatListItemIcon,
    MatSuffix,
    MatPrefix,
    MatMenu,
    MatMenuTrigger,
    MatMenuItem,
  ],
  templateUrl: './role-and-permissions.component.html',
  styleUrl: './role-and-permissions.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
  encapsulation: ViewEncapsulation.None
})
export class RoleAndPermissionsComponent {
  private expandedNodes = signal(new Set<string>());
  // flatNodes = signal<ResourceRow[]>([]);
  flatNodes = computed(() => {
    const data = this.data1();
    const expanded = this.expandedNodes();
    return this.updateFlatNodes(data, expanded);
  });
  roleServices: RolesService = inject(RolesService);
  resourceService: ResourcesService = inject(ResourcesService)
  data1 = computed(() => {
    return this.resourceService.mapperOfData(this.resourceService.resourcesResponse(), this.roleServices.roles());
  })
  linkHistory = signal<Link[]>([{label: "Role && Permissions", navLink: BASE_ADMIN_ROUTE + "/study-plan"}])
  isColumnPanelOpen = signal<boolean>(false);

  context = {permission: "permission", role: "role"}
  @ViewChild('confirmationDialogContent') contentConfirmDialog!: TemplateRef<any>;

  updateDisplayedColumn(column: string) {
    this.checkedcolumn.update((currentSet) => {
      if (currentSet.has(column)) {
        currentSet.delete(column)
      } else {
        currentSet.add(column)
      }
      return new Set(currentSet.values());
    })
  }

  private getAllRoles() {
    console.log("get all roles")
    this.roleServices.getAll().subscribe({
      next: (res) => {
        this.roleServices.roles.set(res?.data?.content);
        this.checkedcolumn.update(() => {
          return new Set(this.roleServices.roles().map((role) => role.name.split("_").map(el => el.toString()
            .toLowerCase()).join(" ")))
        })
        console.log("from get roles success", this.roleServices.roles())
      },
      error: (err) => {
        console.log(err)
      }
    })
  }

  private getAllResources() {
    this.resourceService.getAll().subscribe({
      next: (res) => {
        this.resourceService.resourcesResponse.set(res?.data?.content)


      },
      error: (err) => {
        console.log(err)
      }
    })
  }

  columns = computed(() => {
    const rolesCol = this.roleServices.roles().map((role) => {
      return {
        columnDef: role.name,
        header: role.name.split("_").map(el => el.toString().toLowerCase()).join(" "),
        cell: (element: ResourceRow) => element?.[role.name],
        id: role.id,
        isActionOpen: false
      };
    });

    return [
      {
        columnDef: "Resources",
        header: "Resources",
        cell: (element: ResourceRow) => `${element['resource']}`,
        id: 0
      },
      ...rolesCol,
    ];
  });
  checkedcolumn = signal<Set<string>>(new Set())

  displayedColumns = computed(() => {
    const checkedHeaders = this.checkedcolumn()
    return this.columns()
      .filter((column) => {
        return checkedHeaders.has(column.header) || column.header == "Resources"
      })
      .map((column) => column.columnDef);
  });

  hasChild(node: ResourceRow): boolean {
    return !!node.permissions && node.permissions.length > 0;
  }


  isExpanded(node: ResourceRow): boolean {
    return this.expandedNodes().has(node?.['resource'] as string);
  }


  toggle(node: ResourceRow): void {
    this.expandedNodes.update(currentNodes => {
      const nodeKey = node?.['resource'] as string;
      if (currentNodes.has(nodeKey)) {
        currentNodes.delete(nodeKey);
      } else {
        currentNodes.add(nodeKey);
      }
      return new Set(currentNodes);
    });
  }

  private updateFlatNodes(
    nodes: ResourceRow[],
    expandedNodes: Set<string>
  ): ResourceRow[] {

    const flattenedNodes = this.flattenNodes(nodes, expandedNodes);
    return flattenedNodes;
  }

  private flattenNodes(
    nodes: ResourceRow[],
    expandedNodes: Set<string>
  ): ResourceRow[] {
    const result: ResourceRow[] = [];
    const addNode = (node: ResourceRow) => {
      result.push(node);
      if (node.permissions && this.isNodeExpanded(node, expandedNodes)) {
        node.permissions.forEach((child) => addNode(child));
      }
    };
    nodes.forEach((node) => addNode(node));
    return result;
  }

  private isNodeExpanded(
    node: ResourceRow,
    expandedNodes: Set<string>
  ): boolean {
    return expandedNodes.has(node?.['resource'] as string);
  }

  partiallyComplete = (node: ResourceRow, column: string): boolean => {
    if (!node.permissions) {
      return false;
    }
    return (
      node.permissions.some(item => item[column] === true &&
        !node.permissions.every(item => item[column] === true)
      ))
  }


// update(node: ResourceRow, completed: boolean, column: string) {
//   console.log("update the flatnodes")
//   this.flatNodes.update((nodes) => {
//     const targetNode = nodes.find((n) => n == node) as ResourceRow
//     if (targetNode.level === 0) {
//       targetNode[column] = completed;
//       if (targetNode.permissions) {
//         targetNode.permissions.forEach((item) => {
//           item[column] = completed
//         });
//       }
//     } else {
//       nodes[nodes.indexOf(node)][column] = completed;
//       const parent: ResourceRow = this.findParent(node, nodes);
//       if (parent.permissions) {
//         parent[column] = parent?.permissions.every(item => item[column]) ?? true;
//
//       }
//     }
//     return [...nodes]
//   })
//   console.log("\n the fate node result egale = ", this.flatNodes(), " the checked = ", completed)
//
// }
//
//   private
//
//   findParent(node
//                :
//                ResourceRow, nodes
//                :
//                ResourceRow[]
//   ):
//     ResourceRow {
//     let counter = nodes.indexOf(node)
//     while (nodes[counter].level != 0) {
//       counter--
//     }
//     return nodes[counter]
//   }

  ngOnInit() {

    this.getAllRoles()
    this.getAllResources()
  }

// dialog logic
  dialog: MatDialog = inject(MatDialog)

  openPopupAddRole(title: string, roleField?: string, idRole?: number) {

    const dialogueRef = this.dialog.open(AddRoleDialogueComponent, {
      data: {
        role: new RoleRequest(roleField, idRole), okText: "Submit",
        cancelText: "Cancel", title: title
      },
      width: "450px"
    })
    dialogueRef.afterClosed().subscribe(result => {
      if (result) {
        this.getAllRoles()
        this.getAllResources()
      }
    })
  }

  openPopupDeleteRole(contentConfirmDialog: TemplateRef<any>, role: string, id: number) {
    const dialogRef = this.dialog.open(ConfirmDialogueComponent,
      {
        data: {content: contentConfirmDialog, context: {role}, okText: "Continue", cancelText: "Cancel"},
        width: "460px"
      })
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteRole(id)
      }
    })
  }

  openPopup(contentConfirmDialog: TemplateRef<any>,
            context: ConfirmDialogContext,
            node: any,
            checked: boolean,
            columnDef: string,
            event: MatCheckboxChange,
            roleId: number
  ) {
    const dialogRef = this.dialog.open(ConfirmDialogueComponent, {
      data: {content: contentConfirmDialog, context: context, okText: "Continue", cancelText: "Cancel"},
      width: "460px"
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        event.source.checked = checked;
        //  this.update(node, checked, columnDef)
        let permissions: number[] = [];
        if (node.level) {
          permissions.push(node?.id)
        } else {
          permissions = node.permissions.map((perm) => perm.id)
        }
        if (context.title == "add") {
          this.addPermissionToRole(roleId, permissions)
        } else {
          this.removePermissionFromRole(roleId, permissions)
        }

      } else {
      }
    });

  }


  confirmAddPermission(event: MatCheckboxChange,
                       node: ResourceRow,
                       checked: boolean,
                       columnDef: string,
                       contentConfirmDialog: TemplateRef<any>,
                       roleId: number
  ) {
    event.source.checked = !checked;
    console.log("hello from dialog")
    const listPermissions = node.level ? [node?.resource] : node.permissions.map(el => el?.resource)
    const title: string = checked ? "add" : "remove"
    this.openPopup(contentConfirmDialog, {permissions: listPermissions, role: columnDef, title: title},
      node, checked, columnDef, event, roleId
    )

  }


  private addPermissionToRole(role: number,
                              permissions: number[]
  ) {
    console.log("add permission")
    this.roleServices.addPermissionsToRole(new ManagePermissionRequest(role, permissions))
      .subscribe({
        next: (res) => {
          this.getAllRoles()
          this.getAllResources()
          // this.dataSubject.next(this.data1());
        },
        error: (err) => {
          console.log(err)
        }
      })
  }


  private removePermissionFromRole(role: number,
                                   permissions: number[]
  ) {
    this.roleServices.removePermissionsFromRole(new ManagePermissionRequest(role, permissions))
      .subscribe({
        next: (res) => {

          this.getAllRoles()
          this.getAllResources()
        },
        error: (err) => {
          console.log(err)
        }
      })
  }

  private deleteRole(id: number) {
    this.roleServices.delete(id).subscribe({
      next: (res) => {
        this.getAllRoles()
        this.getAllResources()
        console.log("success delete ")
      },
      error: (err) => {
        console.log("error delete ", err)
      }
    })
  }


}


