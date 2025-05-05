package guilherme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RainAlertAPI {
    public static void main(String[] args) {
        SpringApplication.run(RainAlertAPI.class, args);
    }
}