import { IPatientform, NewPatientform } from './patientform.model';

export const sampleWithRequiredData: IPatientform = {
  id: 11407,
  pathologie: 'grey payment incremental',
};

export const sampleWithPartialData: IPatientform = {
  id: 41622,
  pathologie: 'Kids orchestration scalable',
  typeobservation: 'Plastic Wyoming firewall',
};

export const sampleWithFullData: IPatientform = {
  id: 46923,
  pathologie: 'mobile context-sensitive',
  typeobservation: 'neural',
  casfamiliaux: 'alliance hacking Colorado',
};

export const sampleWithNewData: NewPatientform = {
  pathologie: 'utilize ivory Mauritius',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
