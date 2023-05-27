import { etype } from 'app/entities/enumerations/etype.model';

export interface IStructure {
  id: number;
  name?: string | null;
  stype?: etype | null;
}

export type NewStructure = Omit<IStructure, 'id'> & { id: null };
