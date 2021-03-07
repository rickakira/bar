import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoProdutoListComponent } from './tipo-produto-list.component';

describe('TipoProdutoListComponent', () => {
  let component: TipoProdutoListComponent;
  let fixture: ComponentFixture<TipoProdutoListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TipoProdutoListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TipoProdutoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
