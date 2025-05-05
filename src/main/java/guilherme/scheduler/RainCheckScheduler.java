package guilherme.scheduler;

import guilherme.service.RainAnalyzer;
import guilherme.service.WeatherService;
import guilherme.service.NotificationService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RainCheckScheduler {

    private final WeatherService weatherService;
    private final RainAnalyzer rainAnalyzer;
    private final NotificationService notificationService;

    public RainCheckScheduler(WeatherService weatherService, RainAnalyzer rainAnalyzer, NotificationService notificationService) {
        this.weatherService = weatherService;
        this.rainAnalyzer = rainAnalyzer;
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void checkForRain() {
        weatherService.getRainProbability().ifPresent(prob -> {
            if (rainAnalyzer.shouldAlert(prob)) {
                notificationService.sendRainAlert(prob);
            }
        });
    }
}
