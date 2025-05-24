import { Component, OnInit, OnDestroy } from '@angular/core';
import { ApiService } from '../api.service';
import { HttpErrorResponse } from '@angular/common/http';
import { first, Subscription } from 'rxjs';
import { Brand } from '../models/brand.model';

@Component({
  selector: 'app-listitem',
  templateUrl: './listitem.component.html',
  styleUrls: ['./listitem.component.css'],
})
export class ListitemComponent implements OnInit, OnDestroy {
  title = 'BrandFrontend';
  oszlopok=[
    {key: "id", text: "ID", type: "number"},
    {key: "name", text: "Name", type: "text"},
    {key: "brand", text: "Brand", type: "text"},
    {key: "type", text: "Type", type: "text"},
    {key: "price", text: "Price", type: "number"},
    {key: "image_url", text: "Image URL", type: "text"}
  ];
  brands: any;
  newBrand: any = {};
  feliratkozas!: Subscription;
  error = false;
  errorText = "";

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.getBrand();
  }

  ngOnDestroy(): void {
    if (this.feliratkozas) this.feliratkozas.unsubscribe();
  }

  getBrand(): void {
    this.feliratkozas = this.apiService.getBrand().subscribe({
      next: (res: any) => {
        this.brands = res;
        this.error = false;
      },
      error: (err: HttpErrorResponse) => {
        console.log(err);
        this.error = true;
        this.errorText = err.message;
      }
    });
  }

  createBrand(): void {
    this.apiService.createBrand(this.newBrand).subscribe(() => {
      this.getBrand();
      this.newBrand = {};
    });
  }

  updateBrand(Brand: any): void {
    this.apiService.updateBrand(Brand, Brand.id).subscribe(() => this.getBrand());
  }

  patchBrand(brand: any, id: number): void {
    this.apiService.getBrand().pipe(
      first()).subscribe(
      (brands:any)=> {
        const currentBrand = brands.find((x:any) => x.id === id)!
        const updatedKeys: string[] = ["id"]
        console.log(currentBrand, brand)
        for (const [k, v] of Object.entries(brand)){
          if (currentBrand[k]!= v){
            updatedKeys.push(k)
          }
        }
        const reqBody = Object.fromEntries(Object.entries(brand).filter(([k, v]: any) => updatedKeys.includes(k)))
        this.apiService.patchBrand(reqBody, id).subscribe(()=> this.getBrand())
      }
    )
  }

  deleteBrand(id: number): void {
    this.apiService.deleteBrand(id).subscribe(() => this.getBrand());
  }
}
