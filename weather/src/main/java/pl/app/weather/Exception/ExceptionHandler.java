package pl.app.weather.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(WeatherException.class)
    public ResponseEntity<?> weatherHandler(WeatherException weatherException){
        Error e = new Error(weatherException.getMessage(),weatherException.getStatusCode());
        return new ResponseEntity<>(e,weatherException.getStatusCode());
    }
}
