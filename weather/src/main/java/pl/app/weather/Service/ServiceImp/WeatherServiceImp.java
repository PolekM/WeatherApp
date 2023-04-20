package pl.app.weather.Service.ServiceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.app.weather.Dto.Weather;
import pl.app.weather.Dto.WeatherDto;
import pl.app.weather.Exception.WeatherException;
import pl.app.weather.Service.WeatherService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.app.weather.ApiKey.apiKey;
import static pl.app.weather.Configuration.BeanConfig.BASEURL;


@Service
public class WeatherServiceImp implements WeatherService {


    private final RestTemplate restTemplate;
    private final ModelMapper modelMapper;

    @Autowired
    public WeatherServiceImp(RestTemplate restTemplate, ModelMapper modelMapper) {
        this.restTemplate = restTemplate;
        this.modelMapper = modelMapper;
    }

    @Override
    public WeatherDto getWeather(String name) {
        Weather weather;
        try {
            weather = restTemplate.getForObject(BASEURL + "/current.json?key=" + apiKey + "&q=" + name, Weather.class);
        } catch (HttpClientErrorException httpClientErrorException) {
            throw new WeatherException(httpClientErrorException.getStatusCode(), "City doesn't exist");
        }


        return weatherDto(weather);
    }

    @Override
    public List<WeatherDto> getWeatherWarsawTokyoNewYork() {

        Weather warsaw = restTemplate.getForObject(BASEURL + "/current.json?key=" + apiKey + "&q=Warsaw", Weather.class);
        Weather tokyo = restTemplate.getForObject(BASEURL + "/current.json?key=" + apiKey + "&q=Tokyo", Weather.class);
        Weather newYork = restTemplate.getForObject(BASEURL + "/current.json?key=" + apiKey + "&q=New York", Weather.class);
        return Stream.of(warsaw, tokyo, newYork).map(this::weatherDto).collect(Collectors.toList());
    }

    public WeatherDto weatherDto(Weather weather) {
        return modelMapper.map(weather, WeatherDto.class);

    }
}
