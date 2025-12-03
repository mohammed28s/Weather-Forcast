package weather.com;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
@SpringBootApplication
public class WeatherApplication {    // Weather Forecasting

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

}
