import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { WeatherDto } from '../dto/WeatherDto';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  private baseUrl: String = "http://localhost:8080/weather"

  

  constructor(private httpClient: HttpClient) { }

  getWeatherWarsawTokyoNewYork(): Observable<WeatherDto[]>{
    return this.httpClient.get<WeatherDto[]>(`${this.baseUrl}`)
  }
  getWeather(name: String): Observable<WeatherDto>{
      return this.httpClient.get<WeatherDto>(`${this.baseUrl}/${name}`);
      
  }

}
