import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app';
import { Title } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { RouterTestingModule } from '@angular/router/testing';
describe('App', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppComponent,CommonModule,RouterTestingModule],
    }).compileComponents();
  });

 /* it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });*/

  it('should render title', async () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    const titleService = TestBed.inject(Title);
    /*await fixture.whenStable();
     const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Hello, frontend');*/
    expect(titleService.getTitle()).toBe('kalpashram');
  });
});
