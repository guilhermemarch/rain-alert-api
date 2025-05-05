package guilherme.service;

import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Value("${twilio.account-sid}")
    private String sid;

    @Value("${twilio.auth-token}")
    private String token;

    @Value("${twilio.from-number}")
    private String from;

    @Value("${twilio.to-number}")
    private String to;

    @PostConstruct
    public void init() {
        Twilio.init(sid, token);
    }

    public void sendRainAlert(Double rainProbability) {
        String msg = "Alerta de chuva para Cerro Largo! Chance de chover hoje: " + rainProbability + "%.";
        Message.creator(new PhoneNumber(to), new PhoneNumber(from), msg).create();
    }
}
