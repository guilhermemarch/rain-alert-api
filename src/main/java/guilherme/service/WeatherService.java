package guilherme.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Optional;
import org.json.JSONObject;


@Service
public class WeatherService {

    @Value("${weather.api-key}")
    private String apiKey;

    @Value("${weather.base-url}")
    private String baseUrl;

    @Value("${weather.city}")
    private String city;

    public Optional<Double> getRainProbability() {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .queryParam("q", city)
                    .queryParam("appid", apiKey)
                    .queryParam("units", "metric")
                    .toUriString();

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);
            JSONObject json = new JSONObject(response);

            if (json.has("rain")) {
                JSONObject rain = json.getJSONObject("rain");
                if (rain.has("1h")) {
                    return Optional.of(rain.getDouble("1h") * 100);
                }
            }

            return Optional.of(0.0);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}