import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { StructureFormService, StructureFormGroup } from './structure-form.service';
import { IStructure } from '../structure.model';
import { StructureService } from '../service/structure.service';
import { etype } from 'app/entities/enumerations/etype.model';

@Component({
  selector: 'jhi-structure-update',
  templateUrl: './structure-update.component.html',
})
export class StructureUpdateComponent implements OnInit {
  isSaving = false;
  structure: IStructure | null = null;
  etypeValues = Object.keys(etype);

  editForm: StructureFormGroup = this.structureFormService.createStructureFormGroup();

  constructor(
    protected structureService: StructureService,
    protected structureFormService: StructureFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ structure }) => {
      this.structure = structure;
      if (structure) {
        this.updateForm(structure);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const structure = this.structureFormService.getStructure(this.editForm);
    if (structure.id !== null) {
      this.subscribeToSaveResponse(this.structureService.update(structure));
    } else {
      this.subscribeToSaveResponse(this.structureService.create(structure));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStructure>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(structure: IStructure): void {
    this.structure = structure;
    this.structureFormService.resetForm(this.editForm, structure);
  }
}
