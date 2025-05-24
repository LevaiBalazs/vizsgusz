import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from './environments/environment';




@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private url = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getBrand(): any {
    return this.http.get(`${this.url}/brands`);
  }

  createBrand(brand: any): any {
    return this.http.post(`${this.url}/brands`, brand);
  }

  updateBrand(brand:any,id: number): any {
    return this.http.put(`${this.url}/brands/${brand.id}`, brand);
  }

  patchBrand(brand: any, id: number): any { 
    console.log(`Patching brand with ID: ${id}`, brand);
    return this.http.patch(`${this.url}/brands/${id}`, brand); 
  }
  deleteBrand(id: number): any {
    return this.http.delete(`${this.url}/brands/${id}`);
  }

}
