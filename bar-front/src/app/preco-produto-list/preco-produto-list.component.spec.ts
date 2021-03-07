import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { PrecoProdutoListComponent } from './preco-produto-list.component';

describe('PrecoProdutoListComponent', () => {
  let component: PrecoProdutoListComponent;
  let fixture: ComponentFixture<PrecoProdutoListComponent>;

  beforeEach(waitForAsync(() => {
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
