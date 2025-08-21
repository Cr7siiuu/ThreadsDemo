import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { ActivatedRoute } from '@angular/router';
import { NgFor, NgForOf } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatButtonModule,
    MatCardModule,
    MatGridListModule,
    NgFor,
    NgForOf],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private route: ActivatedRoute){}
[x: string]: any;
  cards = [
    { title: 'Statistiques', subtitle: 'Vue d’ensemble', content: 'KPI et métriques importantes.' },
    { title: 'Messages', subtitle: 'Derniers messages', content: 'Consulter les messages récents.' },
    { title: 'Paramètres', subtitle: 'Configuration', content: 'Accéder aux paramètres de l’application.' },
  ];
  currentYear = new Date().getFullYear();
}
