import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IPatientform } from '../patientform.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../patientform.test-samples';

import { PatientformService } from './patientform.service';

const requireRestSample: IPatientform = {
  ...sampleWithRequiredData,
};

describe('Patientform Service', () => {
  let service: PatientformService;
  let httpMock: HttpTestingController;
  let expectedResult: IPatientform | IPatientform[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(PatientformService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Patientform', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    describe('addPatientformToCollectionIfMissing', () => {
      it('should add a Patientform to an empty array', () => {
        const patientform: IPatientform = sampleWithRequiredData;
        expectedResult = service.addPatientformToCollectionIfMissing([], patientform);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(patientform);
      });

      it('should not add a Patientform to an array that contains it', () => {
        const patientform: IPatientform = sampleWithRequiredData;
        const patientformCollection: IPatientform[] = [
          {
            ...patientform,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addPatientformToCollectionIfMissing(patientformCollection, patientform);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Patientform to an array that doesn't contain it", () => {
        const patientform: IPatientform = sampleWithRequiredData;
        const patientformCollection: IPatientform[] = [sampleWithPartialData];
        expectedResult = service.addPatientformToCollectionIfMissing(patientformCollection, patientform);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(patientform);
      });

      it('should add only unique Patientform to an array', () => {
        const patientformArray: IPatientform[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const patientformCollection: IPatientform[] = [sampleWithRequiredData];
        expectedResult = service.addPatientformToCollectionIfMissing(patientformCollection, ...patientformArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const patientform: IPatientform = sampleWithRequiredData;
        const patientform2: IPatientform = sampleWithPartialData;
        expectedResult = service.addPatientformToCollectionIfMissing([], patientform, patientform2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(patientform);
        expect(expectedResult).toContain(patientform2);
      });

      it('should accept null and undefined values', () => {
        const patientform: IPatientform = sampleWithRequiredData;
        expectedResult = service.addPatientformToCollectionIfMissing([], null, patientform, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(patientform);
      });

      it('should return initial array if no Patientform is added', () => {
        const patientformCollection: IPatientform[] = [sampleWithRequiredData];
        expectedResult = service.addPatientformToCollectionIfMissing(patientformCollection, undefined, null);
        expect(expectedResult).toEqual(patientformCollection);
      });
    });

    describe('comparePatientform', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.comparePatientform(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.comparePatientform(entity1, entity2);
        const compareResult2 = service.comparePatientform(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.comparePatientform(entity1, entity2);
        const compareResult2 = service.comparePatientform(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.comparePatientform(entity1, entity2);
        const compareResult2 = service.comparePatientform(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
