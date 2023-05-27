import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IPatientform } from '../patientform.model';
import { PatientformService } from '../service/patientform.service';

@Injectable({ providedIn: 'root' })
export class PatientformRoutingResolveService implements Resolve<IPatientform | null> {
  constructor(protected service: PatientformService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPatientform | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((patientform: HttpResponse<IPatientform>) => {
          if (patientform.body) {
            return of(patientform.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
