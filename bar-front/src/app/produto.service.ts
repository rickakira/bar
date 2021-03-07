import { Injectable } from '@angular/core';  
import { HttpClient } from '@angular/common/http';  
import { Observable } from 'rxjs';  
  
@Injectable({  
  providedIn: 'root'  
})  
export class ProdutoService {

  private baseUrl = 'http://localhost:8090/api/produto';  
  
  constructor(private http:HttpClient) { }  
  
  getProdutoList(): Observable<any> {  
    return this.http.get(`${this.baseUrl}`);  
  }  
  
  save(produto: object): Observable<Object> {  
    return this.http.post(`${this.baseUrl}`, produto);  
  }  
  
  delete(id: number): Observable<any> {  
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });  
  }  
  
  getProdutoById(id: number): Observable<Object> {  
    return this.http.get(`${this.baseUrl}/${id}`);  
  }  

  getProdutoByDescricao(descricao: String): Observable<Object> {  
    return this.http.get(`${this.baseUrl}/descricao/${descricao}`);  
  }  
  

  update(produto: object, id: number): Observable<Object> {  
    return this.http.put(`${this.baseUrl}/${id}`, produto, { responseType: 'text'} );  
  }  
}
