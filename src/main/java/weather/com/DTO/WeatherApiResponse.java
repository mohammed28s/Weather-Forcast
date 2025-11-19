package weather.com.DTO;


import lombok.Data;

@Data
public class WeatherApiResponse {  // This is the weather API response "External API": https://api.open-meteo.com/v1/

    private CurrentWeather currentWeather;


    @Data
    public static class CurrentWeather {

        private Double temperature;
        private Double windspeed;
        private String time;
    }

}
