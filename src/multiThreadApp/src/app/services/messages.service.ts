import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessagesInterf } from '../interfaces/messages';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {
  private url : string = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public addMessage(message: MessagesInterf){
    return this.http.post<MessagesInterf>(this.url+'/messages',message)
  }

  public getAllMessages():Observable<MessagesInterf[]> {
    return this.http.get<MessagesInterf[]>(this.url+'/allMessages')
  }
}
