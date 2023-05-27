import { IStructure } from 'app/entities/structure/structure.model';

export interface IDepartement {
  id: number;
  name?: string | null;
  chief?: string | null;
  structure?: Pick<IStructure, 'id'> | null;
}

export type NewDepartement = Omit<IDepartement, 'id'> & { id: null };
