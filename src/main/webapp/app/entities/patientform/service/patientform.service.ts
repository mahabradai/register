import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPatientform, NewPatientform } from '../patientform.model';

export type PartialUpdatePatientform = Partial<IPatientform> & Pick<IPatientform, 'id'>;

export type EntityResponseType = HttpResponse<IPatientform>;
export type EntityArrayResponseType = HttpResponse<IPatientform[]>;

@Injectable({ providedIn: 'root' })
export class PatientformService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/patientforms');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPatientform>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPatientform[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getPatientformIdentifier(patientform: Pick<IPatientform, 'id'>): number {
    return patientform.id;
  }

  comparePatientform(o1: Pick<IPatientform, 'id'> | null, o2: Pick<IPatientform, 'id'> | null): boolean {
    return o1 && o2 ? this.getPatientformIdentifier(o1) === this.getPatientformIdentifier(o2) : o1 === o2;
  }

  addPatientformToCollectionIfMissing<Type extends Pick<IPatientform, 'id'>>(
    patientformCollection: Type[],
    ...patientformsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const patientforms: Type[] = patientformsToCheck.filter(isPresent);
    if (patientforms.length > 0) {
      const patientformCollectionIdentifiers = patientformCollection.map(
        patientformItem => this.getPatientformIdentifier(patientformItem)!
      );
      const patientformsToAdd = patientforms.filter(patientformItem => {
        const patientformIdentifier = this.getPatientformIdentifier(patientformItem);
        if (patientformCollectionIdentifiers.includes(patientformIdentifier)) {
          return false;
        }
        patientformCollectionIdentifiers.push(patientformIdentifier);
        return true;
      });
      return [...patientformsToAdd, ...patientformCollection];
    }
    return patientformCollection;
  }
}
