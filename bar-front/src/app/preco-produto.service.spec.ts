import { TestBed } from '@angular/core/testing';

import { PrecoProdutoService } from './preco-produto.service';

describe('PrecoProdutoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PrecoProdutoService = TestBed.get(PrecoProdutoService);
    expect(service).toBeTruthy();
  });
});
