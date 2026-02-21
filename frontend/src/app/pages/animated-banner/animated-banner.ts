import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-animated-banner',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './animated-banner.html',
  styleUrls: ['./animated-banner.css']
})
export class AnimatedBannerComponent {
  @Input() title: string = '';
  @Input() lines: string[] = [];
  /** Optional background: you can use a color code or one of these presets */
    @Input() bgColor: string = '#fdf6e3'; // default soft cream

    /** Preset themes */
    presetThemes: { [key: string]: string } = {
      gold: '#c59d5f',
      cream: '#fdf6e3',
      charcoal: '#2c2c2c',
      peach: '#f5d6b1'
    };

}
