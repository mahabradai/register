import { etype } from 'app/entities/enumerations/etype.model';

import { IStructure, NewStructure } from './structure.model';

export const sampleWithRequiredData: IStructure = {
  id: 50985,
  name: 'Account FTP',
};

export const sampleWithPartialData: IStructure = {
  id: 74811,
  name: 'incremental hack',
};

export const sampleWithFullData: IStructure = {
  id: 73242,
  name: 'deliver SMTP bottom-line',
  stype: etype['CLIPRIV'],
};

export const sampleWithNewData: NewStructure = {
  name: 'Shirt',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
