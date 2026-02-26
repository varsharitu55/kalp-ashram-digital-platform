import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './pages/navbar/navbar';
import { FooterComponent } from './pages/footer/footer';
import { Title } from '@angular/platform-browser';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule,NavbarComponent, FooterComponent, RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class AppComponent {
  title = 'kalpashram';

  // 1. You MUST declare the property here so the HTML can see it
  userForm: FormGroup;

  private fb = inject(FormBuilder);
  private http = inject(HttpClient);
  backendErrors: any = {};
  private titleService = inject(Title);
  constructor() {
    this.titleService.setTitle(this.title);

    // 2. Now you initialize it
    this.userForm = this.fb.group({
      name: ['', Validators.required],
      mobileNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      email: ['', [Validators.required, Validators.email]],
      userRole: ['', Validators.required]
    });
  }

  onRegister() {
      const userData = this.userForm.value;

      this.http.post('http://localhost:8080/api/registerUser', userData)
        .subscribe({
          next: (response : any) => {
            console.log('Success!', response);
            alert('User created in MySQL!');
            this.userForm.reset();
            this.backendErrors = {}; // Clear errors on success
          },
          error: (err : any) => {
            console.error('Registration failed', err);
            if (err.status === 400) {
              // This displays the validation messages you wrote in Java!
              this.backendErrors = err.error;
            } else {
              alert('Backend is offline. Is your Spring Boot running?');
            }
          }
        });
    }
  }





