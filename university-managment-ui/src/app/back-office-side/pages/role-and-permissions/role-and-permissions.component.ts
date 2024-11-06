import {ChangeDetectionStrategy, Component, computed, signal, ViewChild, ViewEncapsulation} from '@angular/core';
import {JsonPipe, NgIf} from "@angular/common";
import {MatTable, MatTableDataSource, MatTableModule} from "@angular/material/table";
import {MatTreeModule} from "@angular/material/tree";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {BehaviorSubject} from "rxjs";
import {MatCheckbox} from "@angular/material/checkbox";

interface PeriodicElement {
  position: number;
  name: string;
  weight: number;
  symbol: string;
  level: number;
  children?: PeriodicElement[];
}


interface PermissionC {
  permission: string,
  admin: Item
  Manager: Item,
  Moderator: Item,
  SuperAdmin: Item,
}

interface Item {
  name: string
  completed: boolean
}

interface PermissionP {
  Resources: string,
  Admin: Item
  Manager: Item,
  Moderator: Item,
  SuperAdmin: Item,
  level: number,
  children?: PermissionP[]
}

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
  ],
  templateUrl: './role-and-permissions.component.html',
  styleUrl: './role-and-permissions.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
  encapsulation: ViewEncapsulation.None
})
export class RoleAndPermissionsComponent {

  private expandedNodes = new Set<string>();
  private dataSubject = new BehaviorSubject<PermissionP[]>([]);
  flatNodes = signal<PermissionP[]>([]);

  // Sample data
  private treeDataA: PermissionP[] = [
    {
      Resources: "department",
      Admin: {name: "Admin", completed: false},
      Manager: {name: "Admin", completed: false},
      Moderator: {name: "Admin", completed: false},
      SuperAdmin: {name: "Admin", completed: false},
      level: 0,
      children: [
        {
          Resources: "update",
          Admin: {name: "Admin", completed: false},
          Manager: {name: "Admin", completed: false},
          Moderator: {name: "Admin", completed: false},
          SuperAdmin: {name: "Admin", completed: false},
          level: 1,
        }, {
          Resources: "View",
          Admin: {name: "Admin", completed: false},
          Manager: {name: "Admin", completed: false},
          Moderator: {name: "Admin", completed: false},
          SuperAdmin: {name: "Admin", completed: false},
          level: 1,
        }, {
          Resources: "delete",
          Admin: {name: "Admin", completed: false},
          Manager: {name: "Admin", completed: false},
          Moderator: {name: "Admin", completed: false},
          SuperAdmin: {name: "Admin", completed: false},
          level: 1,
        }, {
          Resources: "Add",
          Admin: {name: "Admin", completed: false},
          Manager: {name: "Admin", completed: false},
          Moderator: {name: "Admin", completed: false},
          SuperAdmin: {name: "Admin", completed: false},
          level: 1,
        },
      ],
    }

  ];
  columns = signal([
    {
      columnDef: 'Resources',
      header: 'Resources',
      cell: (element: PermissionP) => `${element.Resources}`,
    }, {
      columnDef: 'Admin',
      header: 'Admin',
      cell: (element: PermissionP) => element.Admin,
    }, {
      columnDef: 'SuperAdmin',
      header: 'SuperAdmin',
      cell: (element: PermissionP) => element.SuperAdmin,
    }, {
      columnDef: 'Moderator',
      header: 'Moderator',
      cell: (element: PermissionP) => element.Moderator,
    }, {
      columnDef: 'Manager',
      header: 'Manager',
      cell: (element: PermissionP) => element.Manager,
    },
  ])
  displayedColumns = computed(() => this.columns().map((cl) => cl.columnDef));
  dataSource = new MatTableDataSource<PermissionP>()
  @ViewChild(MatTable) table!: MatTable<PermissionP>;

  ngOnInit() {

    this.dataSubject.subscribe(() => {
      this.updateFlatNodes()
    });


    this.dataSubject.next(this.treeDataA);
    console.log("this data ", this.flatNodes)

  }


  hasChild(node: PermissionP): boolean {
    return !!node.children && node.children.length > 0;
  }


  isExpanded(node: PermissionP): boolean {
    return this.expandedNodes.has(node.Resources);
  }


  toggle(node: PermissionP): void {
    console.log('node ', node);
    if (this.isExpanded(node)) {
      this.expandedNodes.delete(node.Resources
      );
    } else {
      this.expandedNodes.add(node.Resources);
    }
    this.updateFlatNodes();
  }


  private updateFlatNodes(): void {
    this.flatNodes.set(this.flattenNodes(this.dataSubject.value));
    this.dataSource.data = this.flatNodes()
    console.log("flten data output ", this.flatNodes)

  }

  private flattenNodes(nodes: PermissionP[]): PermissionP[] {
    const result: PermissionP[] = [];

    const addNode = (node: PermissionP) => {
      result.push(node);

      if (node.children && this.isExpanded(node)) {
        node.children.forEach((child) => addNode(child));
      }
    };

    nodes.forEach((node) => addNode(node));

    console.log("result input ", nodes)
    console.log("result output ", result)
    return result;
  }

  partiallyComplete = (node: PermissionP, column: string): boolean => {
    if (!node.children) {
      return false;
    }

    return (
      node.children.some(item => item[column]?.completed) &&
      !node.children.every(item => item[column]?.completed)
    );
  }


  update(node: PermissionP, completed: boolean, column: string) {
    this.flatNodes.update((nodes) => {
      const targetNode = nodes.find((n) => n == node) as PermissionP
      if (targetNode.level === 0) {
        targetNode[column].completed = completed;
        if (targetNode.children) {
          targetNode.children.forEach((item) => {
            console.log("item children", item)
            item[column].completed = completed
            console.log("item[column].completed ", item[column].completed)

          });
        }
      } else {
        nodes[nodes.indexOf(node)][column].completed = completed;
        const parent: PermissionP = this.findParent(node, nodes);
        console.log("the parent = ", parent)
        if (parent.children) {
          parent[column].completed = parent?.children.every(item => item[column].completed) ?? true;

        }
      }
      this.table.renderRows();
      return [...nodes]
    })
    this.dataSource.data = this.flatNodes()
    console.log("the check node ", this.flatNodes())
  }

  private findParent(node: PermissionP, nodes: PermissionP[]): PermissionP {
    let counter = nodes.indexOf(node)
    while (nodes[counter].level != 0) {
      counter--
    }
    return nodes[counter]
  }

}
