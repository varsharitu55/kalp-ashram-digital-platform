import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimatedBannerComponent } from './animated-banner';

describe('AnimatedBanner', () => {
  let component: AnimatedBannerComponent;
  let fixture: ComponentFixture<AnimatedBannerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnimatedBannerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnimatedBannerComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
