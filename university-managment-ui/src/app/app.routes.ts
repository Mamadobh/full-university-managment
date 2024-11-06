import {Routes} from '@angular/router';
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {ActualityComponent} from "./pages/actuality/actuality.component";
import {StudyPlanComponent} from "./pages/study-plan-pages/study-plan/study-plan.component";
import {StudyPlanDisplayComponent} from "./pages/study-plan-pages/study-plan-display/study-plan-display.component";
import {StudyPlanCreateComponent} from "./pages/study-plan-pages/study-plan-create/study-plan-create.component";
import {RecapStudyPlanComponent} from "./pages/study-plan-pages/recap-study-plan/recap-study-plan.component";
import {AdminLayoutComponent} from "./layout/admin-layout/admin-layout.component";
import {AuthBackOfficeComponent} from "./back-office-side/auth/auth-back-office/auth-back-office.component";
import {BackOfficeComponent} from "./back-office-side/back-office/back-office.component";
import {
  RoleAndPermissionsComponent
} from "./back-office-side/pages/role-and-permissions/role-and-permissions.component";

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'back-office/views/dashboard'
  },
  {
    path: 'back-office',
    component: BackOfficeComponent,
    children: [
      {
        path: 'views',
        component: AdminLayoutComponent,
        children: [
          {
            path: 'dashboard',
            component: DashboardComponent
          },
          {
            path: 'actuality',
            component: ActualityComponent
          },
          {
            path: 'study-plan',
            component: StudyPlanComponent,
          },
          {
            path: 'study-plan/:levelId',
            component: StudyPlanDisplayComponent
          },
          {
            path: 'study-plan/:levelId/create',
            component: StudyPlanCreateComponent
          },
          {
            path: 'study-plan/:levelId/recap',
            component: RecapStudyPlanComponent
          },
          {
            path: 'Roles&&Permissions',
            component: RoleAndPermissionsComponent
          },
        ]
      },
      {
        path: 'auth',
        component: AuthBackOfficeComponent
      }
    ]
  },
  {
    path: '**',
    redirectTo: 'back-office/views/dashboard'
  }
];
