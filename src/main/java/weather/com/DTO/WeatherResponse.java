package weather.com.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResponse {


        @JsonProperty("temperature")
        private Double temperature;

        @JsonProperty("windspeed")
        private Double windspeed;

        @JsonProperty("time")
        private String time;

}
