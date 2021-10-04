import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipePlaceSearchComponent } from './recipe-place-search.component';

describe('RecipePlaceSearchComponent', () => {
  let component: RecipePlaceSearchComponent;
  let fixture: ComponentFixture<RecipePlaceSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipePlaceSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipePlaceSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
