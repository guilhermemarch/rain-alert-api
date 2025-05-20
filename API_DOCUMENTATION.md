# API Documentation

### Authentication

All endpoints require JWT authentication. Include the token in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

### User Management

#### Register User
```http
POST /api/users
Content-Type: application/json

{
    "name": "Guilherme M",
    "email": "guilhermem@gmail.com",
    "password": "minhasenha123",
    "phoneNumber": "+55283892382",
    "location": {
        "name": "MinhaCasa",
        "latitude": 40.7128,
        "longitude": -74.0060,
        "city": "Cerro Largo",
        "state": "RS",
        "country": "Brazil"
    },
    "notificationPreferences": {
        "emailEnabled": true,
        "smsEnabled": true,
        "pushEnabled": false,
        "preferredNotificationTime": "08:00",
        "rainThreshold": 70
    }
}
```

Response:
```json
{
    "id": 1,
    "name": "Guilherme M",
    "email": "guilhermem@gmail.com",
    "phoneNumber": "+55232832782",
    "location": {
        "name": "MInhaCasa",
        "latitude": 40.7128,
        "longitude": -74.0060,
        "city": "Cerro Largo",
        "state": "RS",
        "country": "Brazil"
    },
    "notificationPreferences": {
        "emailEnabled": true,
        "smsEnabled": true,
        "pushEnabled": false,
        "preferredNotificationTime": "08:00",
        "rainThreshold": 70
    }
}
```

#### Get User Profile
```http
GET /api/users/{id}
```

Response:
```json
{
    "id": 1,
    "name": "Guilherme M",
    "email": "guilhermem@gmail.com",
    "phoneNumber": "+55283892382",
    "location": {
        "name": "MinhaCasa",
        "latitude": 40.7128,
        "longitude": -74.0060,
        "city": "Cerro Largo",
        "state": "RS",
        "country": "Brazil"
    },
    "notificationPreferences": {
        "emailEnabled": true,
        "smsEnabled": true,
        "pushEnabled": false,
        "preferredNotificationTime": "08:00",
        "rainThreshold": 70
    }
}
```

#### Update User Profile
```http
PUT /api/users/{id}
Content-Type: application/json

{
    "name": "Guilherme Atualizado",
    "phoneNumber": "+55283892382",
    "location": {
        "name": "Trabalho",
        "latitude": 40.7128,
        "longitude": -74.0060,
        "city": "Cerro Largo",
        "state": "RS",
        "country": "Brazil"
    }
}
```

Response:
```json
{
    "id": 1,
    "name": "Guilherme Atualizado",
    "email": "guilhermem@gmail.com",
    "phoneNumber": "+55283892382",
    "location": {
        "name": "Trabalho",
        "latitude": 40.7128,
        "longitude": -74.0060,
        "city": "Cerro Largo",
        "state": "RS",
        "country": "Brazil"
    },
    "notificationPreferences": {
        "emailEnabled": true,
        "smsEnabled": true,
        "pushEnabled": false,
        "preferredNotificationTime": "08:00",
        "rainThreshold": 70
    }
}
```

#### Delete User
```http
DELETE /api/users/{id}
```

Response: 204 No Content

### Location Management

#### Add Location
```http
POST /api/users/{id}/locations
Content-Type: application/json

{
    "name": "Casa de Praia",
    "latitude": 34.0522,
    "longitude": -118.2437,
    "city": "Los Angeles",
    "state": "CA",
    "country": "USA",
    "alertBoundary": {
        "radiusKm": 5.0
    }
}
```

Response:
```json
{
    "id": 2,
    "name": "Casa de Praia",
    "latitude": 34.0522,
    "longitude": -118.2437,
    "city": "Los Angeles",
    "state": "CA",
    "country": "USA",
    "alertBoundary": {
        "id": 1,
        "radiusKm": 5.0,
        "minLatitude": 34.0072,
        "maxLatitude": 34.0972,
        "minLongitude": -118.2937,
        "maxLongitude": -118.1937
    }
}
```

#### Update Location
```http
PUT /api/users/{id}/locations/{locationId}
Content-Type: application/json

{
    "name": "Casa de Praia Atualizada",
    "alertBoundary": {
        "radiusKm": 10.0
    }
}
```

Response:
```json
{
    "id": 2,
    "name": "Casa de Praia Atualizada",
    "latitude": 34.0522,
    "longitude": -118.2437,
    "city": "Los Angeles",
    "state": "CA",
    "country": "USA",
    "alertBoundary": {
        "id": 1,
        "radiusKm": 10.0,
        "minLatitude": 33.9572,
        "maxLatitude": 34.1472,
        "minLongitude": -118.3437,
        "maxLongitude": -118.1437
    }
}
```

#### Delete Location
```http
DELETE /api/users/{id}/locations/{locationId}
```

Response: 204 No Content

### Notification Preferences

#### Update Notification Preferences
```http
PUT /api/users/{id}/notifications
Content-Type: application/json

{
    "emailEnabled": true,
    "smsEnabled": false,
    "pushEnabled": true,
    "preferredNotificationTime": "09:00",
    "rainThreshold": 60
}
```

Response:
```json
{
    "emailEnabled": true,
    "smsEnabled": false,
    "pushEnabled": true,
    "preferredNotificationTime": "09:00",
    "rainThreshold": 60
}
```

#### Get Notification Preferences
```http
GET /api/users/{id}/notifications
```

Response:
```json
{
    "emailEnabled": true,
    "smsEnabled": false,
    "pushEnabled": true,
    "preferredNotificationTime": "09:00",
    "rainThreshold": 60
}
```

### Weather Data

#### Get Current Weather
```http
GET /api/weather/current?latitude=40.7128&longitude=-74.0060
```

Response:
```json
{
    "temperature": 22.5,
    "humidity": 65,
    "windSpeed": 10.5,
    "rainProbability": 30,
    "description": "Partly cloudy",
    "timestamp": "2024-05-06T10:00:00Z"
}
```

#### Get Weather Forecast
```http
GET /api/weather/forecast?latitude=40.7128&longitude=-74.0060&days=3
```

Response:
```json
{
    "forecast": [
        {
            "date": "2024-05-06",
            "maxTemperature": 25.0,
            "minTemperature": 18.0,
            "rainProbability": 30,
            "description": "Partly cloudy"
        },
        {
            "date": "2024-05-07",
            "maxTemperature": 26.0,
            "minTemperature": 19.0,
            "rainProbability": 70,
            "description": "Light rain"
        },
        {
            "date": "2024-05-08",
            "maxTemperature": 24.0,
            "minTemperature": 17.0,
            "rainProbability": 20,
            "description": "Sunny"
        }
    ]
}
```

## Error Responses

```json
{
    "error": "Bad Request",
    "message": "Invalid input data",
    "status": 400
}
```

```json
{
    "error": "Unauthorized",
    "message": "Invalid or expired token",
    "status": 401
}
```

```json
{
    "error": "Forbidden",
    "message": "Insufficient permissions",
    "status": 403
}
```

```json
{
    "error": "Not Found",
    "message": "Resource not found",
    "status": 404
}
```

```json
{
    "error": "Internal Server Error",
    "message": "An unexpected error occurred",
    "status": 500
}
```

## Rate Limiting

- 100 requests per minute for authenticated users
- 20 requests per minute for unauthenticated users

## Monitoring

```http
GET /actuator/health
```

Response:
```json
{
    "status": "UP",
    "components": {
        "db": {
            "status": "UP"
        },
        "diskSpace": {
            "status": "UP"
        },
        "mail": {
            "status": "UP"
        }
    }
}
```
