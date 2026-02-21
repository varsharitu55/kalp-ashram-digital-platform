import { Component } from '@angular/core';
import { EnquiryFormComponent } from '../../pages/enquiry-form/enquiry-form';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [EnquiryFormComponent],
  templateUrl: './contact.html',
  styleUrl: './contact.css',
})
export class ContactComponent {

}
