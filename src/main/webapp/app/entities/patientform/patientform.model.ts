export interface IPatientform {
  id: number;
  pathologie?: string | null;
  typeobservation?: string | null;
  casfamiliaux?: string | null;
}

export type NewPatientform = Omit<IPatientform, 'id'> & { id: null };
