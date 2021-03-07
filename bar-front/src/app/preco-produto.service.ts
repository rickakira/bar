
import { Injectable } from '@angular/core';  
import { HttpClient } from '@angular/common/http';  
import { Observable } from 'rxjs';  
  
@Injectable({  
  providedIn: 'root'  
})  

export class PrecoProdutoService {

  private baseUrl = 'http://localhost:8090/api/produto/preco';  
  
  constructor(private http:HttpClient) { }  
  
  getPrecoProdutoList(): Observable<any> {  
    return this.http.get(`${this.baseUrl}`);  
  }  
  
  save(precoProduto: object): Observable<Object> {  
    return this.http.post(`${this.baseUrl}`, precoProduto);  
  }  
  
  delete(id: number): Observable<any> {  
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });  
  }  
  
  getMarcaById(id: number): Observable<Object> {  
    return this.http.get(`${this.baseUrl}/${id}`);  
  }  

  update(precoProduto: object, id: number): Observable<Object> {  
    return this.http.put(`${this.baseUrl}/${id}`, precoProduto, { responseType: 'text'} );  
  }  
}

