import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-enquiry-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule   // <-- REQUIRED for formGroup + formControlName
  ],
  templateUrl: './enquiry-form.html',
  styleUrls: ['./enquiry-form.css']
})
export class EnquiryFormComponent {

  enquiryForm: FormGroup;

  private fb = inject(FormBuilder);
  private http = inject(HttpClient);

  constructor() {
    this.enquiryForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.email]],
      phone: ['', Validators.required],
      message: ['', Validators.required]
    });
  }

  submitEnquiry() {
    const data = this.enquiryForm.value;

    this.http.post('http://localhost:8080/api/enquiry', data)
      .subscribe({
        next: () => {
          alert('Enquiry sent successfully!');
          this.enquiryForm.reset();
        },
        error: () => {
          alert('Failed to send enquiry. Check backend.');
        }
      });
  }
}
