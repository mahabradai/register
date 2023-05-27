import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PatientformComponent } from '../list/patientform.component';
import { PatientformDetailComponent } from '../detail/patientform-detail.component';
import { PatientformRoutingResolveService } from './patientform-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const patientformRoute: Routes = [
  {
    path: '',
    component: PatientformComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PatientformDetailComponent,
    resolve: {
      patientform: PatientformRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(patientformRoute)],
  exports: [RouterModule],
})
export class PatientformRoutingModule {}
