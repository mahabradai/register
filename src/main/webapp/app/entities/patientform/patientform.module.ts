import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { PatientformComponent } from './list/patientform.component';
import { PatientformDetailComponent } from './detail/patientform-detail.component';
import { PatientformRoutingModule } from './route/patientform-routing.module';

@NgModule({
  imports: [SharedModule, PatientformRoutingModule],
  declarations: [PatientformComponent, PatientformDetailComponent],
})
export class PatientformModule {}
