package guilherme.service;

import guilherme.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    private final RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.base-url}")
    private String baseUrl;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "weather", key = "#location.latitude + ',' + #location.longitude")
    public Optional<Double> getRainProbability(Location location) {
        try {
            String url = String.format("%s/forecast.json?key=%s&q=%f,%f&days=1&aqi=no&alerts=no",
                baseUrl, apiKey, location.getLatitude(), location.getLongitude());
            
            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
            
            if (response != null && response.getForecast() != null && !response.getForecast().getForecastday().isEmpty()) {
                return Optional.of(response.getForecast().getForecastday().get(0).getDay().getDailyChanceOfRain());
            }
            
            return Optional.empty();
        } catch (Exception e) {
            log.error("Failed to fetch weather data for location: {}", location, e);
            return Optional.empty();
        }
    }

    public WeatherStats getWeatherStats(Location location) {
        try {
            String url = String.format("%s/forecast.json?key=%s&q=%f,%f&days=7&aqi=no&alerts=no",
                baseUrl, apiKey, location.getLatitude(), location.getLongitude());
            
            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
            
            if (response != null && response.getForecast() != null) {
                double maxProb = response.getForecast().getForecastday().stream()
                    .mapToDouble(day -> day.getDay().getDailyChanceOfRain())
                    .max()
                    .orElse(0.0);
                
                double avgProb = response.getForecast().getForecastday().stream()
                    .mapToDouble(day -> day.getDay().getDailyChanceOfRain())
                    .average()
                    .orElse(0.0);
                
                return new WeatherStats(maxProb, avgProb);
            }
            
            return new WeatherStats(0.0, 0.0);
        } catch (Exception e) {
            log.error("Failed to fetch weather stats for location: {}", location, e);
            return new WeatherStats(0.0, 0.0);
        }
    }
}

class WeatherResponse {
    private Forecast forecast;

    public WeatherResponse() {
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}

class Forecast {
    private List<ForecastDay> forecastday;

    public Forecast() {
    }

    public List<ForecastDay> getForecastday() {
        return forecastday;
    }

    public void setForecastday(List<ForecastDay> forecastday) {
        this.forecastday = forecastday;
    }
}

class ForecastDay {
    private Day day;

    public ForecastDay() {
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}

class Day {
    private double daily_chance_of_rain;

    public Day() {
    }

    public double getDailyChanceOfRain() {
        return daily_chance_of_rain;
    }

    public void setDailyChanceOfRain(double daily_chance_of_rain) {
        this.daily_chance_of_rain = daily_chance_of_rain;
    }
}