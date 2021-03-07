import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrecoProdutoComponent } from './preco-produto.component';

describe('PrecoProdutoComponent', () => {
  let component: PrecoProdutoComponent;
  let fixture: ComponentFixture<PrecoProdutoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrecoProdutoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrecoProdutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
