import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Matrix } from '../domain/matrix';
import { Observable } from 'rxjs';
import { GlobalConstants } from '../app-global.constants'

@Injectable({
  providedIn: 'root'
})
export class MultiplyService {

  private multiplyUrl: string;
 
  constructor(private http: HttpClient) {
    this.multiplyUrl = GlobalConstants.url + '/multiply';
  }

  public multiplyMatrices(matrices: Matrix[]): Observable<any>{
    return this.http.post<any>(this.multiplyUrl, matrices)
  }
}
