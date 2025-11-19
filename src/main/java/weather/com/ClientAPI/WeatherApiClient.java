package weather.com.ClientAPI;




import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import weather.com.DTO.WeatherResponse;

@Component
@RequiredArgsConstructor
public class WeatherApiClient {

    @Autowired
    private RestClient restClient;


    @Value("${weather.api.base-url}")    // This is client API or endpoint
    private String baseURl;


    public WeatherResponse getWeather(Double latitude, Double longitude) {

        return restClient.get()
                .uri(baseURl + "forecast?latitude={lat}&longitude={lon}&current_weather=true",
                        latitude, longitude)
                .retrieve()
                .body(WeatherResponse.class);
    }


}
