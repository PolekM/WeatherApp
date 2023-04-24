package pl.app.weather.Service.ServiceImp;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;
import pl.app.weather.Dto.*;
import pl.app.weather.Service.WeatherService;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class WeatherServiceImpTest {

    @Mock
    RestTemplate restTemplate;
    ModelMapper modelMapper = new ModelMapper();

    @Test
    void GetWeatherWithParamWarsawShouldReturnLocationNameWarsaw() {
        //given
        WeatherService weatherService = new WeatherServiceImp(restTemplate,modelMapper);
        //when
        when(restTemplate.getForObject(anyString(), any())).thenReturn(prepareSingleMockData());
        WeatherDto result = weatherService.getWeather("Warsaw");
        //then
        assertThat(result.getLocationName(),is("Warsaw"));

    }

    @Test
    void getWeatherWarsawTokyoNewYorkShouldReturnListOfThreeElement() {
        //given
        restTemplate = mock(RestTemplate.class);
        WeatherService weatherService = new WeatherServiceImp(restTemplate,modelMapper);

        Location locationWarsaw = new Location("Warsaw","Poland");
        Current currentWarsaw = new Current(0,18.0,new Condition("Sunny","//url",1000));
        Weather warsaw = new Weather(locationWarsaw,currentWarsaw);

        Location locationTokyo = new Location("Tokyo","Japan");
        Current currentTokyo = new Current(0,15.0,new Condition("Sunny","//url",1000));
        Weather tokyo = new Weather(locationTokyo,currentTokyo);

        Location locationNewYork = new Location("New York","USA");
        Current currentNewYork = new Current(0,20.0,new Condition("Sunny","//url",1000));
        Weather newYork = new Weather(locationNewYork,currentNewYork);

        //when
        when(restTemplate.getForObject(anyString(), any())).thenReturn(warsaw,tokyo,newYork);
        List<WeatherDto> result = weatherService.getWeatherWarsawTokyoNewYork();
        //then
        assertThat(result.size(),is(3));
        assertThat(result.get(0).getLocationName(),is("Warsaw"));
        assertThat(result.get(1).getLocationName(),is("Tokyo"));
        assertThat(result.get(2).getLocationName(),is("New York"));

    }

    private Weather prepareSingleMockData(){
        Location location = new Location("Warsaw","Poland");
        Current current = new Current(0,20.0,new Condition("Sunny","//url",1000));
        Weather weather = new Weather(location,current);
        return weather;
    }




}