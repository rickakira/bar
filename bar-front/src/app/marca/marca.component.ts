import { Component, OnInit } from '@angular/core';
import {FormControl,FormGroup,Validators} from '@angular/forms';  

import { MarcaService } from '../marca.service';
import { Marca } from '../marca';

@Component({
  selector: 'app-marca',
  templateUrl: './marca.component.html',
  styleUrls: ['./marca.component.css']
})
export class MarcaComponent implements OnInit {

  constructor(private marcaService: MarcaService) { }

  marca: Marca = new Marca();
  submitted = false;

  ngOnInit() {
    this.submitted = false;
  }

  form = new FormGroup({
    descricao: new FormControl( '',[ Validators.required, Validators.minLength(1) ] )
  })

  saveMarca(saveMarca) {
    this.marca = new Marca();
    this.marca.descricao = this.Descricao.value;
    this.submitted = true;

    this.save();
  }

  save() {
    this.marcaService.save(this.marca)
                      .subscribe( data => console.log( data ), error => console.error( error ) );
    this.marca = new Marca();                  
  }

  get Descricao() {
    return this.form.get('descricao') ;
  }

  addForm() {
    this.submitted = false;
    this.form.reset();
  }

}
