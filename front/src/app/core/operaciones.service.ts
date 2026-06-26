import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Operacion } from './operacion.model';


@Injectable({
  providedIn: 'root'
})
export class OperacionService {
  private apiUrl = environment.endpoint + 'fx'; 

  constructor(private http: HttpClient) {}

  registrar(op: Operacion): Observable<Operacion> {
    return this.http.post<Operacion>(`${this.apiUrl}/operaciones`, op);
  }

  listar(estado?: string): Observable<Operacion[]> {
    const url = estado ? `${this.apiUrl}/operaciones?estado=${estado}` : `${this.apiUrl}/operaciones`;
    return this.http.get<Operacion[]>(url);
  }

  actualizarEstado(id: number, nuevoEstado: string): Observable<Operacion> {
    return this.http.patch<Operacion>(`${this.apiUrl}/${id}/estado`, { estado: nuevoEstado });
  }
}
