import {Routes} from '@angular/router';
import {DashboardComponent} from "./back-office-side/pages/dashboard/dashboard.component";
import {ActualityComponent} from "./back-office-side/pages/actuality/actuality.component";
import {StudyPlanComponent} from "./back-office-side/pages/study-plan-pages/study-plan/study-plan.component";
import {
  StudyPlanDisplayComponent
} from "./back-office-side/pages/study-plan-pages/study-plan-display/study-plan-display.component";
import {
  StudyPlanCreateComponent
} from "./back-office-side/pages/study-plan-pages/study-plan-create/study-plan-create.component";
import {
  RecapStudyPlanComponent
} from "./back-office-side/pages/study-plan-pages/recap-study-plan/recap-study-plan.component";
import {AdminLayoutComponent} from "./layout/admin-layout/admin-layout.component";
import {BackOfficeComponent} from "./back-office-side/back-office/back-office.component";
import {
  RoleAndPermissionsComponent
} from "./back-office-side/pages/role-and-permissions/role-and-permissions.component";
import {AuthBackOfficeComponent} from "./back-office-side/auth/auth-back-office/auth-back-office.component";
import {authGuard, protectAdminLoginGuard} from "./core/auth/auth.guard";

export const routes: Routes = [

  {
    path: 'back-office',
    component: BackOfficeComponent,
    children: [
      {
        path: 'views',
        component: AdminLayoutComponent,
        canActivate: [authGuard],

        children: [
          {
            path: 'dashboard',
            component: DashboardComponent,
            canActivate: [authGuard],

          },
          {
            path: 'actuality',
            component: ActualityComponent,
            canActivate: [authGuard],
          },
          {
            path: 'study-plan',
            component: StudyPlanComponent,
            canActivate: [authGuard],
          },
          {
            path: 'study-plan/:levelId',
            component: StudyPlanDisplayComponent,
            canActivate: [authGuard],
          },
          {
            path: 'study-plan/:levelId/create',
            component: StudyPlanCreateComponent,
            canActivate: [authGuard],
          },
          {
            path: 'study-plan/:levelId/recap',
            component: RecapStudyPlanComponent,
            canActivate: [authGuard],
          },
          {
            path: 'Roles&&Permissions',
            component: RoleAndPermissionsComponent,
            canActivate: [authGuard],
          },
        ]
      },
      {
        path: 'auth',
        component: AuthBackOfficeComponent,
        canActivate: [protectAdminLoginGuard],
      }
    ]
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'back-office/views/dashboard',
  },
  {
    path: '**',
    redirectTo: 'back-office/views/dashboard'
  }
];
