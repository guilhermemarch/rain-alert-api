# Rain Alert API

This is a Spring Boot API that checks weather for Cerro Largo and sends a WhatsApp alert if rain probability is high. ##Note: in future, I intend to add features can allow adding other locations.

## Features

- Consumes OpenWeatherMap API
- Analyzes rain probability
- Sends WhatsApp alert via Twilio
- Scheduled checks every hour

## Setup

1. Set your API keys in `application.yml`
2. Run with Maven:

```bash
./mvnw spring-boot:run
```

## Configuration

Edit `src/main/resources/application.yml`:

- `weather.api-key`: Your weather API key
- `twilio.account-sid`: Twilio SID
- `twilio.auth-token`: Twilio token
- `twilio.from-number`: WhatsApp sender
- `twilio.to-number`: WhatsApp recipient

## To get the APIs Keys, you must:
 - https://openweathermap.org/api (is free)
 - https://www.twilio.com/docs/iam/api-keys (is free)
