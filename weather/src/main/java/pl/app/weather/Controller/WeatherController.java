package pl.app.weather.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.app.weather.Dto.WeatherDto;
import pl.app.weather.Service.WeatherService;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WeatherController {

    private final WeatherService weatherService;


    @Autowired
    public WeatherController(WeatherService mainService) {
        this.weatherService = mainService;
    }

    @GetMapping("/weather/{name}")
    public WeatherDto getWeather(@PathVariable("name") String name) {
        return weatherService.getWeather(name);
    }

    @GetMapping("/weather")
    public List<WeatherDto> getWeatherWarsawTokyoNewYork() {
        return weatherService.getWeatherWarsawTokyoNewYork();
    }
}
