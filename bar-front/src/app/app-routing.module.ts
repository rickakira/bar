import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TipoProdutoComponent } from './tipo-produto/tipo-produto.component';
import { TipoProdutoListComponent } from './tipo-produto-list/tipo-produto-list.component';
import { MarcaComponent } from './marca/marca.component';
import { MarcaListComponent } from './marca-list/marca-list.component';
import { ProdutoComponent } from './produto/produto.component';
import { ProdutoListComponent } from './produto-list/produto-list.component';
import { PrecoProdutoComponent } from './preco-produto/preco-produto.component';
import { PrecoProdutoListComponent } from './preco-produto-list/preco-produto-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'view-preco-produto', pathMatch: 'full' },
  { path: 'view-tipo-produto', component: TipoProdutoListComponent },
  { path: 'view-preco-produto', component: PrecoProdutoListComponent },
  { path: 'view-produto', component: ProdutoListComponent },
  { path: 'view-marca', component: MarcaListComponent },
  { path: 'add-tipo-produto', component: TipoProdutoComponent },
  { path: 'add-preco-produto', component: PrecoProdutoComponent },
  { path: 'add-produto', component: ProdutoComponent },
  { path: 'add-marca', component: MarcaComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
