import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPatientform } from '../patientform.model';

@Component({
  selector: 'jhi-patientform-detail',
  templateUrl: './patientform-detail.component.html',
})
export class PatientformDetailComponent implements OnInit {
  patientform: IPatientform | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ patientform }) => {
      this.patientform = patientform;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
