import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthComponent } from '../../pages/auth/auth';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule,AuthComponent],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class NavbarComponent {

}
