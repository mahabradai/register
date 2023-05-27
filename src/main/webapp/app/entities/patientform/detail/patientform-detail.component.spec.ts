import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PatientformDetailComponent } from './patientform-detail.component';

describe('Patientform Management Detail Component', () => {
  let comp: PatientformDetailComponent;
  let fixture: ComponentFixture<PatientformDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PatientformDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ patientform: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(PatientformDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(PatientformDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load patientform on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.patientform).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
