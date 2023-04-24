package pl.app.weather.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {

    private String locationName;
    private String locationCountry;
    private int currentIsDay;
    private double currentTemp_c;
    private String conditionCurrentText;
    private String conditionCurrentIcon;
    private int conditionCurrentCode;
}
