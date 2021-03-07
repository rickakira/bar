import { Injectable } from '@angular/core';  
import { HttpClient } from '@angular/common/http';  
import { Observable } from 'rxjs';  
import { Marca } from './marca';
  
@Injectable({  
  providedIn: 'root'  
})  
  
export class MarcaService {  
  
  private baseUrl = 'http://localhost:8090/api/marca';  
  
  constructor(private http:HttpClient) { }  
  
  getMarcaList(): Observable<Marca[]> {  
    return this.http.get<Marca[]>(`${this.baseUrl}`);  
  }  
  
  save(marca: any): Observable<any> {  
    return this.http.post(`${this.baseUrl}`, marca);  
  }  
  
  delete(id: number): Observable<any> {  
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });  
  }  
  
  getMarcaById(id: any): Observable<any> {  
    return this.http.get(`${this.baseUrl}/${id}`);  
  }  

  getMarcaByDescricao(descricao: String): Observable<any> {  
    return this.http.get(`${this.baseUrl}/descricao/${descricao}`);  
  }  
  
  update(marca: any, id: number): Observable<any> {  
    return this.http.put(`${this.baseUrl}/${id}`, marca, { responseType: 'text'} );  
  }  
    
}  