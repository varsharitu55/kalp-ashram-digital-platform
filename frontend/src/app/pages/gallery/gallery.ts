import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-gallery',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './gallery.html',
  styleUrl: './gallery.css',
})
export class GalleryComponent {
   images = [
      { src: 'coverPic.jpg', title: 'Ballroom' },
      { src: 'profilePic.jpg', title: 'Stage View' },
      { src: 'hallFull.JPG', title: 'Main Hall' },
      { src: 'grandEntry.JPG', title: 'Reception Area' },
      { src: 'aaradhyaEntry.JPG', title: 'Ballroom' },
      { src: 'aaradhyaHall.JPG', title: 'Vintage Decor' },

{ src: 'showPark.JPG', title: 'Ceremony' },
{ src: 'profile.jpg', title: 'Ceremony' },




 { src: 'aaradhya.JPG', title: 'Vintage Photo' },


      { src: 'entry.JPG', title: 'Ceremony' },
      { src: 'hallCenter.jpg', title: 'Event Setup' },
      { src: 'show.jpg', title: 'Vintage Photo' },
     { src: 'central.jpg', title: 'Ceremony' },
{ src: 'view.JPG', title: 'Ceremony' },

  { src: 'mandap.jpg', title: 'Vintage Photo' },

         { src: 'enterance.JPG', title: 'Event Setup' },
            { src: 'fullView.JPG', title: 'Vintage Photo' },

{ src: 'entryWedding.JPG', title: 'Vintage Photo' },

{ src: 'dinning.jpg', title: 'Ceremony' },

                  { src: 'stage.jpg', title: 'Vintage Photo' },


    ];}
