import {Routes} from '@angular/router';
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {ActualityComponent} from "./pages/actuality/actuality.component";
import {StudyPlanComponent} from "./pages/study-plan-pages/study-plan/study-plan.component";
import {StudyPlanDisplayComponent} from "./pages/study-plan-pages/study-plan-display/study-plan-display.component";
import {StudyPlanCreateComponent} from "./pages/study-plan-pages/study-plan-create/study-plan-create.component";

export const routes: Routes = [

  {
    path: '',
    pathMatch: "full",
    redirectTo: "dashboard"
  },
  {
    path: "dashboard",
    component: DashboardComponent
  },
  {
    path: "actuality",
    component: ActualityComponent
  },
  {
    path: "study-plan",
    component: StudyPlanComponent,
  },

  {
    path: "study-plan/:levelId",
    component: StudyPlanDisplayComponent
  }, {
    path: "study-plan/:levelId/create",
    component: StudyPlanCreateComponent
  },

];
