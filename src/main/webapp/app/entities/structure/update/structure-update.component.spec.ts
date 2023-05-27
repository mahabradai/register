import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { StructureFormService } from './structure-form.service';
import { StructureService } from '../service/structure.service';
import { IStructure } from '../structure.model';

import { StructureUpdateComponent } from './structure-update.component';

describe('Structure Management Update Component', () => {
  let comp: StructureUpdateComponent;
  let fixture: ComponentFixture<StructureUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let structureFormService: StructureFormService;
  let structureService: StructureService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [StructureUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(StructureUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(StructureUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    structureFormService = TestBed.inject(StructureFormService);
    structureService = TestBed.inject(StructureService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const structure: IStructure = { id: 456 };

      activatedRoute.data = of({ structure });
      comp.ngOnInit();

      expect(comp.structure).toEqual(structure);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStructure>>();
      const structure = { id: 123 };
      jest.spyOn(structureFormService, 'getStructure').mockReturnValue(structure);
      jest.spyOn(structureService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ structure });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: structure }));
      saveSubject.complete();

      // THEN
      expect(structureFormService.getStructure).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(structureService.update).toHaveBeenCalledWith(expect.objectContaining(structure));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStructure>>();
      const structure = { id: 123 };
      jest.spyOn(structureFormService, 'getStructure').mockReturnValue({ id: null });
      jest.spyOn(structureService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ structure: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: structure }));
      saveSubject.complete();

      // THEN
      expect(structureFormService.getStructure).toHaveBeenCalled();
      expect(structureService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IStructure>>();
      const structure = { id: 123 };
      jest.spyOn(structureService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ structure });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(structureService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
