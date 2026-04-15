import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home';
import { AboutComponent } from './pages/about/about';
import { EventsComponent } from './pages/events/events';
import { GalleryComponent } from './pages/gallery/gallery';
import { ContactComponent } from './pages/contact/contact';
import { UserDetailComponent } from './pages/user-detail/user-detail';
import { ProfileComponent } from './pages/profile/profile';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'events', component: EventsComponent },
  { path: 'gallery', component: GalleryComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'user-detail', component: UserDetailComponent },
  { path: 'profile', component: ProfileComponent },
  { path: '**', redirectTo: '' }
];
