import { Component, OnInit } from '@angular/core';
import { WeatherDto } from 'src/app/dto/WeatherDto';
import { WeatherService } from 'src/app/service/weather.service';
import { trigger, transition, animate, style } from '@angular/animations';
@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css'],
  animations: [
    trigger('fadeIn', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('1.5s ease-out', style({ opacity: 1 }))
      ]),
    ])
  ]
})
export class WeatherComponent implements OnInit{

  weatherDto: WeatherDto[] = [];
  singleWeather: WeatherDto;
  cityName: String = "";
  animationState: boolean = true;

  constructor(private weatherService: WeatherService){
    this.singleWeather = {};
  }

  ngOnInit(): void {
    this.getWeatherWarsawTokyoNewYork();
  }
  
  getWeatherWarsawTokyoNewYork(){
    this.weatherService.getWeatherWarsawTokyoNewYork().subscribe(data =>{this.weatherDto = data});
    setInterval(() => {
      this.weatherService.getWeatherWarsawTokyoNewYork().subscribe(data =>{this.weatherDto = data});
    }, 900000 ); // 900000 milis
   
}
  getWeather(name: String){
    this.weatherService.getWeather(name).subscribe(data =>{this.singleWeather = data});
    this.animationState = false;
    setTimeout(() => {
      this.animationState = true;
    }, 10);
  }

}
