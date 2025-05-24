import { Component, OnInit, OnDestroy } from '@angular/core';
import { ApiService } from '../api.service';
import { HttpErrorResponse } from '@angular/common/http';
import { first, min, Subscription } from 'rxjs';

@Component({
  selector: 'app-listitem',
  templateUrl: './listitem.component.html',
  styleUrls: ['./listitem.component.css'],
})
export class ListitemComponent implements OnInit, OnDestroy {
  title = 'BrandFrontend';
  oszlopok = [
    { key: 'id', text: 'ID', type: 'number' },
    { key: 'firstName', text: 'First Name', type: 'text' },
    { key: 'lastName', text: 'Last Name', type: 'text' },
    { key: 'age', text: 'Age', type: 'number' },
    { key: 'occupation', text: 'Occupation', type: 'text' },
  ];
  users: any;
  newUser: any = {};
  sub!: Subscription;
  alertMessage: string | null = null;
  isError: boolean = false;
  timeoutId: any = null;

  constructor(private apiService: ApiService) {}

  

  ngOnInit(): void {
    this.getUsers();
  }

  ngOnDestroy(): void {
    if (this.sub) this.sub.unsubscribe();
  }

  getUsers(): void {
    this.sub = this.apiService.getUser().subscribe({
      next: (res: any) => {
        this.users = res;
      },
      error: (err: HttpErrorResponse) => {
        console.log(err);
        this.showMessage(err.error, true, 5000);
      },
    });
  }

  createUser(): void {
    this.apiService.createUser(this.newUser).subscribe({
      next: (res: any) => {
        this.getUsers();
        this.newUser = {};
        this.showMessage(res, false, 3000);
      },
      error: (err: HttpErrorResponse) => {
        let errorMessage = err.message.toString();
        this.showMessage(errorMessage, true, 5000);
      }
    });
  }

  updateUser(User: any): void {
    this.apiService.updateUser(User, User.id).subscribe({
      next: (res: any) => {
        this.getUsers();
        this.showMessage(res, false, 3000);
      },
      error: (err: HttpErrorResponse) => {
        this.showMessage(err.message, true, 5000);
      },
    });
  }

  deleteUser(id: number): void {
    this.apiService.deleteUser(id).subscribe({
      next: (res: any) => {
        this.getUsers();
        this.showMessage(res, false, 3000);
      },
      error: (err: HttpErrorResponse) => {
        let errorMessage = JSON.stringify(err.message);
        this.showMessage(errorMessage, true, 5000);
      },
    });
  }

  showMessage(msg: string, isError: boolean, duration: number): void {
    this.alertMessage = msg;
    this.isError = isError;
    
    if (this.timeoutId) {
      clearTimeout(this.timeoutId);
    }

    this.timeoutId = setTimeout(() => {
      this.alertMessage = null;
      this.isError = false;
      this.timeoutId = null;
    }, duration);
  }

}
