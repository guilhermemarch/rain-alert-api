# Rain Alert

A Spring Boot application that provides real-time rain alerts based on user locations and preferences.

## Setup

1. **Prerequisites**
   - Java 17 or higher
   - PostgreSQL
   - Maven
   - Gmail account (for email notifications)
   - Twilio account (for SMS notifications)
   - WeatherAPI.com account (for weather updates)

2. **Configuration**
   Update `application.yml` with your credentials:
   ```yaml
   spring:
     mail:
       username: email que vai ser usado
       password: senha_de_app

   weather:
     api:
       key: weatner api key

   twilio:
     account:
       sid: twilio sid
     auth:
       token: twilio token
     phone:
       number: twilio phone
   ```

3. **Database Setup**
   ```sql
   CREATE DATABASE rain_alert;
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```
