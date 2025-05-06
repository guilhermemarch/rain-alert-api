package guilherme.scheduler;

import guilherme.model.Location;
import guilherme.model.User;
import guilherme.service.NotificationService;
import guilherme.service.UserService;
import guilherme.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RainCheckScheduler {
    private static final Logger log = LoggerFactory.getLogger(RainCheckScheduler.class);
    private final UserService userService;
    private final WeatherService weatherService;
    private final NotificationService notificationService;

    public RainCheckScheduler(UserService userService, WeatherService weatherService, NotificationService notificationService) {
        this.userService = userService;
        this.weatherService = weatherService;
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void checkRainProbability() {
        log.info("Starting scheduled rain probability check");
        try {
            User currentUser = userService.getCurrentUser();
            Location location = currentUser.getLocation();

            if (location == null) {
                log.warn("User {} has no location set", currentUser.getId());
                return;
            }

            weatherService.getRainProbability(location).ifPresent(probability -> {
                if (probability >= location.getAlertThreshold()) {
                    notificationService.sendRainAlert(currentUser, location, probability);
                }
            });

        } catch (Exception e) {
            log.error("Error during rain probability check", e);
        }
    }

    @Scheduled(cron = "0 0 20 * * *") 
    public void sendDailySummary() {
        User currentUser = userService.getCurrentUser();
        Location location = currentUser.getLocation();

        if (location != null && currentUser.getNotificationPreferences().isDailySummary()) {
            var stats = weatherService.getWeatherStats(location);
            notificationService.sendDailySummary(currentUser, location, stats.getMaxProbability(), stats.getAverageProbability());
        }
    }
}
