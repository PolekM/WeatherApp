package pl.app.weather.Service;

import pl.app.weather.Dto.WeatherDto;

import java.util.List;

public interface WeatherService {
    WeatherDto getWeather(String name);

    List<WeatherDto> getWeatherWarsawTokyoNewYork();
}
