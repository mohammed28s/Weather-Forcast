package weather.com.Controller;





import lombok.RequiredArgsConstructor;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weather.ExceptionHandling.GlobalExceptionHandler;
import weather.com.DTO.WeatherResponse;
import weather.com.Service.WeatherService;




@RestController
@RequestMapping("/api/weather")   // The main path to the weather APIs
@RequiredArgsConstructor
public class WeatherController {

    @Autowired
    private final WeatherService weatherService;


    Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @GetMapping("/current")  // This is getting all the current Weather forecasting data
    public WeatherResponse getWeather(@RequestParam Double lat, @RequestParam Double lon) {
        logger.trace("The Temp information has been fetched successfully");
        logger.debug("The endpoint has been debug");
        logger.info("This API fetching the latest Temp on the specific Geo location");
        logger.warn("The Weather API is not working");
        logger.error("The has issue fetching the Weather info or the parameters did not be add");

        if (lat == null || lon == null) {   // This is to check if the parameters are not empty
            throw new GlobalExceptionHandler.ResourceNotFoundException("Add the lat and lon parameters");
        }


        try {

            return weatherService.getCurrentWeather(lat, lon);

        } catch (Exception e)
        {
            throw new GlobalExceptionHandler.ResourceNotFoundException("Failed to fetch the weather info");
        }

    }

}
