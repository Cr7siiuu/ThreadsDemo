import { Component, OnInit } from '@angular/core';
import { MessagesInterf } from '../interfaces/messages';
import { MessagesService } from '../services/messages.service';
import {MatTableModule} from '@angular/material/table';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-tab',
  standalone: true,
  imports: [MatTableModule,HttpClientModule],
  templateUrl: './tab.component.html',
  styleUrl: './tab.component.css'
})
export class TabComponent implements OnInit{
   dataSource!: MessagesInterf[];
   displayedColumns: string[] = ['id', 'message'];
   constructor(private messagesService: MessagesService,){
   }
  ngOnInit(): void {
    this.messagesService.getAllMessages().subscribe({
      next: (data)=> this.dataSource = data,
      error: (err) => console.error('Erreur lors du chargement',err)
    })
  }
}
