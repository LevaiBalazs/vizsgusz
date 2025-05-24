import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from './environments/environment';
import { User } from './models/user.model';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';




@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private url = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getUser(): any {
    return this.http.get(`${this.url}/user`);
  }

  createUser(user: any): any {
    return this.http.post(`${this.url}/user`, user, { responseType: 'text' }).pipe(
      catchError((error) => {
        let errorMessage = 'An unknown error occurred';
        if (error.error) {
          const errorObj = JSON.parse(error.error);
          const readable = Object.values(errorObj).join('\n');
          console.log(readable);
          errorMessage = readable;
        }
        return throwError(() => new Error(errorMessage));
      }))
      ;
  }

  updateUser(user: any, id: number): any {
    return this.http.put(`${this.url}/user/${user.id}`, user, { responseType: 'text' }).pipe(
      catchError((error) => {
        let errorMessage = 'An unknown error occurred';
        if (error.error && typeof error.error === 'object') {
          errorMessage = error.error.message || JSON.stringify(error.error);
        } else if (typeof error.error === 'string') {
          errorMessage = error.error;
        }
        return throwError(() => new Error(errorMessage));
      }));
  }

  deleteUser(id: number): any {
    return this.http.delete(`${this.url}/user/${id}`, { responseType: 'text' } ).pipe(
      catchError((error) => {
        let errorMessage = 'An unknown error occurred';
        if (error.error && typeof error.error === 'object') {
          errorMessage = error.error.message || JSON.stringify(error.error);
        } else if (typeof error.error === 'string') {
          errorMessage = error.error;
        }
        return throwError(() => new Error(errorMessage));
      }));
  }

}
