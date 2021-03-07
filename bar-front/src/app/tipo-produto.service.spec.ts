import { TestBed } from '@angular/core/testing';

import { TipoProdutoService } from './tipo-produto.service';

describe('TipoProdutoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TipoProdutoService = TestBed.get(TipoProdutoService);
    expect(service).toBeTruthy();
  });
});
