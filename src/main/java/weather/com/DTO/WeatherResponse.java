package weather.com.DTO;


import lombok.*;

@Data
public class WeatherResponse {

    @Data
    public static class CurrentWeather {

        private Double temperature;
        private Double windspeed;
        private String time;
    }

}
