import { Component } from '@angular/core';
import { HeroComponent } from '../../pages/hero/hero';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    HeroComponent,
    ],
  templateUrl: './home.html',
  styleUrl: './home.css'
})

export class HomeComponent {

}
