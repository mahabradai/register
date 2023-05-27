import { IDepartement, NewDepartement } from './departement.model';

export const sampleWithRequiredData: IDepartement = {
  id: 86141,
  name: 'Bedfordshire Chips Balboa',
};

export const sampleWithPartialData: IDepartement = {
  id: 91414,
  name: 'Nebraska green Shirt',
  chief: 'optical systems Island',
};

export const sampleWithFullData: IDepartement = {
  id: 268,
  name: 'Wooden Group online',
  chief: 'Burkina Loan',
};

export const sampleWithNewData: NewDepartement = {
  name: 'Frozen payment Ohio',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
