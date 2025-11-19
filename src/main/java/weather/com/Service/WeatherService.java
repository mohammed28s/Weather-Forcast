package weather.com.Service;




import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weather.com.ClientAPI.WeatherApiClient;
import weather.com.DTO.WeatherResponse;



@Service
@RequiredArgsConstructor
@Data
public class WeatherService {


    @Autowired
    private WeatherApiClient weatherApiClient;

    public WeatherResponse getCurrentWeather(Double lat, Double lon) {

        return weatherApiClient.getWeather(lat, lon);
    }


}
