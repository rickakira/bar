import { Component, OnInit } from '@angular/core';
import { MarcaService } from '../marca.service';
import { Marca } from '../marca';
import { Observable, Subject } from 'rxjs';
import { FormControl,FormGroup,Validators } from '@angular/forms'; 
import { DataTableModule } from 'angular-datatable';

@Component({
  selector: 'app-marca-list',
  templateUrl: './marca-list.component.html',
  styleUrls: ['./marca-list.component.css']
})
export class MarcaListComponent implements OnInit {
  marcaList?: Marca[];
  currentMarca?: Marca;
  currentIndex: number;
  descricao: '';

  constructor(private marcaService:MarcaService) { }

  ngOnInit(): void {
    this.refreshList();
  }

  retornaMarcas(): void {
    this.marcaService.getMarcaList().subscribe(
      data => {
        this.marcaList = data;
        console.log(data);
      },
      error => {
        console.log(error);
      }  
    )     
  }

  refreshList(): void {
    this.retornaMarcas();
    this.currentMarca = undefined;
    this.currentIndex = -1;
  }

  setActiveMarca(marca: Marca, index: number): void {
    this.currentMarca = marca;
    this.currentIndex = index;
  }

  searchByDescricao(): void {
    this.marcaService.getMarcaByDescricao(this.descricao).subscribe(
      data => {
        this.marcaList = data;
        console.log(data);
      },
      error => {
        console.error(error);
      }
    )
  }
}
