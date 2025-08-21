import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessagesInterf } from '../interfaces/messages';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {
  private url : string = 'http://localhost:8080/api/v1'

  constructor(private http: HttpClient) { }

  public addMessage(message: MessagesInterf){
    return this.http.post<MessagesInterf>(this.url+'/messages',message)
  }

  public getAllMessages():Observable<MessagesInterf[]> {
    return this.http.get<MessagesInterf[]>(this.url+'/allMessages')
  }
}
