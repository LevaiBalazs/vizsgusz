import { Component } from '@angular/core';
import { HomeComponent } from "./home/home.component";
import { ListitemComponent } from './listitem/listitem.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'dogaFE';
}
