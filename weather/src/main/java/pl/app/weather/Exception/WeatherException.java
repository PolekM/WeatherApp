package pl.app.weather.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

@Getter
public class WeatherException extends HttpClientErrorException {

    public WeatherException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
       ;
    }

}
