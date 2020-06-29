import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Matrix } from '../domain/matrix';
import { Observable } from 'rxjs';
import { GlobalConstants } from '../app-global.constants'

@Injectable({
  providedIn: 'root'
})
export class DeterminantService {
  private determinantUrl: string;
 
  constructor(private http: HttpClient) {
    this.determinantUrl = GlobalConstants.url + '/findDeterminant';
  }

  public findDeterminant(matrix: Matrix): Observable<number>{
    return this.http.post<number>(this.determinantUrl, matrix)
  }

}
