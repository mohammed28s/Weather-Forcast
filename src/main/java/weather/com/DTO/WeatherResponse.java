package weather.com.DTO;


import lombok.Data;

@Data
public class WeatherResponse {
    
        private Double temperature;
        private Double windspeed;
        private String time;

}
