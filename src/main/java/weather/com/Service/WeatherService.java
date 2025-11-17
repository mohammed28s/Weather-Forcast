package weather.com.Service;




import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import weather.com.DTO.WeatherResponse;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Data
public class WeatherService {


    @Autowired
    private RestClient restClient;

    public WeatherResponse getCurrentWeather(Double lat, Double lon) {
        String url = "https://api.open-meteo.com/v1/forecast"
                + "?latitude=" + lat
                + "&longitude=" + lon
                + "&current=temperature_2m,wind_speed_10m";

        Map<String, Object> json;
        json = restClient.get()
                .uri(url)
                .retrieve()
                .body(Map.class);

        Map<String, Object> current = (Map<String, Object>) json.get("current");

        WeatherResponse response = new WeatherResponse();
        response.setTemperature((Double) current.get("temperature_2m"));
        response.setWindspeed((Double) current.get("wind_speed_10m"));
        response.setTime((String) current.get("time"));

        return response;
    }


}
