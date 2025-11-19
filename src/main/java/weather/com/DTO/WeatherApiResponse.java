package weather.com.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherApiResponse {  // This is the weather API response "External API": https://api.open-meteo.com/v1/


    @JsonProperty("current_weather")
    private CurrentWeather currentWeather;


    @Data
    public static class CurrentWeather {

        private Double temperature;

        @JsonProperty("windspeed")
        private Double windspeed;

        private String time;
    }

}
