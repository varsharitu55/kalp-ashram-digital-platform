import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule],   // ⭐ THIS WAS MISSING
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class NavbarComponent {

}
