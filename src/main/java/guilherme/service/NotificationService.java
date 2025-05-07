package guilherme.service;

import guilherme.model.Location;
import guilherme.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final JavaMailSender emailSender;

    @Value("${weather.notification.email.from}")
    private String emailFrom;

    @Value("${twilio.account.sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    public NotificationService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendRainAlert(User user, Location location, double probability) {
        if (user.getNotificationPreferences().isEmailEnabled()) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(emailFrom);
                message.setTo(user.getEmail());
                message.setSubject("Rain Alert for " + location.getName());
                message.setText(String.format("There is a %.1f%% chance of rain at %s.", 
                    probability, location.getName()));
                emailSender.send(message);
                log.info("Sent email alert to user {}", user.getId());
            } catch (Exception e) {
                log.error("Failed to send email alert to user {}", user.getId(), e);
            }
        }

        if (user.getNotificationPreferences().isSmsEnabled()) {
            try {
                Twilio.init(twilioAccountSid, twilioAuthToken);
                Message.creator(
                    new PhoneNumber(user.getPhoneNumber()),
                    new PhoneNumber(twilioPhoneNumber),
                    String.format("Rain Alert: %.1f%% chance of rain at %s.", 
                        probability, location.getName())
                ).create();
                log.info("Sent SMS alert to user {}", user.getId());
            } catch (Exception e) {
                log.error("Failed to send SMS alert to user {}", user.getId(), e);
            }
        }
    }

    public void sendDailySummary(User user, Location location, double maxProbability, double avgProbability) {
        if (user.getNotificationPreferences().isEmailEnabled()) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(emailFrom);
                message.setTo(user.getEmail());
                message.setSubject("Daily Weather Summary for " + location.getName());
                message.setText(String.format(
                    "Today's weather summary for %s:\n" +
                    "Maximum chance of rain: %.1f%%\n" +
                    "Average chance of rain: %.1f%%",
                    location.getName(), maxProbability, avgProbability));
                emailSender.send(message);
                log.info("Sent daily summary email to user {}", user.getId());
            } catch (Exception e) {
                log.error("Failed to send daily summary email to user {}", user.getId(), e);
            }
        }
    }
}
