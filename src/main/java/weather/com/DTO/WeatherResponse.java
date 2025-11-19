package weather.com.DTO;



import lombok.Data;

@Data
public class WeatherResponse {   // This is the spring app response will be displayed

        private String time;
        private Double interval;
        private Double temperature;
        private Double windspeed;
        private Double winddirection;
        private Integer is_day;
        private Integer weathercode;
}


