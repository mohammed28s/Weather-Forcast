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

        private Double interval;

        @JsonProperty("windspeed")
        private Double wind_speed;

        @JsonProperty("winddirection")
        private Double wind_direction;

        @JsonProperty("is_day")
        private Integer IsdayOrNight;

        @JsonProperty("weathercode")
        private Integer weather_code;

        private String time;
    }

}
