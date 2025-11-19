package weather.com.ClientAPI;




import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import weather.com.DTO.WeatherApiResponse;
import weather.com.DTO.WeatherResponse;

@Component
@RequiredArgsConstructor
public class WeatherApiClient {

    @Autowired
    private RestClient restClient;


    @Value("${weather.api.base-url}")    // This is client API or endpoint
    private String baseURl;


    public WeatherResponse getWeather(Double latitude, Double longitude) {

        // adding the Query String to the main baseURL
        String url = baseURl + "forecast?latitude=" + latitude +
                               "&longitude=" + longitude +
                               "&current_weather=true";

        //Get API response as WeatherAPIResponse
        WeatherApiResponse apiResponse = restClient.get()
                .uri(url)
                .retrieve()
                .body(WeatherApiResponse.class);


        // Map to your own response  DTO

        // Current_weather
        WeatherResponse response = new WeatherResponse();

        response.setTime(apiResponse.getCurrentWeather().getTime());
        response.setInterval(apiResponse.getCurrentWeather().getInterval());
        response.setTemperature(apiResponse.getCurrentWeather().getTemperature());
        response.setWindspeed(apiResponse.getCurrentWeather().getWind_speed());
        response.setWinddirection(apiResponse.getCurrentWeather().getWind_direction());
        response.setIs_day(apiResponse.getCurrentWeather().getIsdayOrNight());
        response.setWeathercode(apiResponse.getCurrentWeather().getWeather_code());


        return response;

    }

}
