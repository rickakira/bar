import { BrowserModule } from '@angular/platform-browser';  
import { NgModule } from '@angular/core';  

import { AppRoutingModule } from './app-routing.module';  
import { AppComponent } from './app.component';  
import { FormsModule, ReactiveFormsModule } from '@angular/forms';  
import { HttpClientModule } from '@angular/common/http';  
import {DataTablesModule} from 'angular-datatables';  
import { TipoProdutoComponent } from './tipo-produto/tipo-produto.component';
import { MarcaComponent } from './marca/marca.component';
import { ProdutoComponent } from './produto/produto.component';
import { PrecoProdutoComponent } from './preco-produto/preco-produto.component';
import { MarcaListComponent } from './marca-list/marca-list.component';
import { TipoProdutoListComponent } from './tipo-produto-list/tipo-produto-list.component';
import { ProdutoListComponent } from './produto-list/produto-list.component';
import { PrecoProdutoListComponent } from './preco-produto-list/preco-produto-list.component';

@NgModule({
  declarations: [
    AppComponent,
    TipoProdutoComponent,
    MarcaComponent,
    ProdutoComponent,
    PrecoProdutoComponent,
    MarcaListComponent,
    TipoProdutoListComponent,
    ProdutoListComponent,
    PrecoProdutoListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,  
    ReactiveFormsModule,  
    HttpClientModule,  
    DataTablesModule  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
