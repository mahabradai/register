import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'structure',
        data: { pageTitle: 'Structures' },
        loadChildren: () => import('./structure/structure.module').then(m => m.StructureModule),
      },
      {
        path: 'patientform',
        data: { pageTitle: 'Patientforms' },
        loadChildren: () => import('./patientform/patientform.module').then(m => m.PatientformModule),
      },
      {
        path: 'departement',
        data: { pageTitle: 'Departements' },
        loadChildren: () => import('./departement/departement.module').then(m => m.DepartementModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
