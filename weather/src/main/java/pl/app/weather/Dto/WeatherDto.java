package pl.app.weather.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDto {

    private String locationName;
    private String locationCountry;
    private int currentIsDay;
    private double currentTemp_c;
    private String conditionCurrentText;
    private String conditionCurrentIcon;
    private int conditionCurrentCode;
}
