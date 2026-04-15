import { Component, OnInit } from '@angular/core';
import { Title, Meta } from '@angular/platform-browser';
import { HeroComponent } from '../../pages/hero/hero';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [HeroComponent],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class HomeComponent implements OnInit {

  constructor(private title: Title, private meta: Meta) {}

  ngOnInit() {
    this.title.setTitle('Kalp Ashram | Best Banquet Hall for Weddings & Events');

    this.meta.updateTag({
      name: 'description',
      content: 'Book Kalp Ashram for weddings, birthdays and corporate events. 500+ capacity with AC & Non-AC rooms and parking.'
     });
   this.meta.updateTag({
     name: 'keywords',
     content: 'banquet hall, wedding hall, event venue, Kalp Ashram, AC rooms, party hall'
   });
  }

}
