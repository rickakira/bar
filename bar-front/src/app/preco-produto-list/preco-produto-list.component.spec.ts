import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrecoProdutoListComponent } from './preco-produto-list.component';

describe('PrecoProdutoListComponent', () => {
  let component: PrecoProdutoListComponent;
  let fixture: ComponentFixture<PrecoProdutoListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrecoProdutoListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrecoProdutoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
