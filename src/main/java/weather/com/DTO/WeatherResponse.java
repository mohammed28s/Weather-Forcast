package weather.com.DTO;



import lombok.Data;

@Data
public class WeatherResponse {   // This is the spring app response will be displayed

        private Double temperature;
        private Double windspeed;
        private String time;
    }


