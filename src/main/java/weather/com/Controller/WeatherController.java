package weather.com.Controller;





import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import weather.com.DTO.WeatherResponse;
import weather.com.Service.WeatherService;

@Service
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;



    @GetMapping  // This is getting all the current Weather forecasting data
    public WeatherResponse getWeather(@RequestParam Double lat, @RequestParam Double lon){
        return weatherService.getCurrentWeather(lat, lon);
    }



}
