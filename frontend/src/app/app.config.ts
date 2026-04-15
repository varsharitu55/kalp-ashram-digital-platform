import { ApplicationConfig, provideBrowserGlobalErrorListeners , APP_INITIALIZER } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { AuthService } from './core/services/auth';


function initializeKeycloak(authService: AuthService) {
  return () => authService.init();
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideHttpClient(),
    AuthService,
        {
          provide: APP_INITIALIZER,
          useFactory: initializeKeycloak,
          deps: [AuthService],
          multi: true
        }
  ]
};




