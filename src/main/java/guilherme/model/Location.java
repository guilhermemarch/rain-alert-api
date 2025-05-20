package guilherme.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Embeddable
public class Location {
    @Column(name = "location_name", nullable = false)
    private String name;

    @Column(name = "location_latitude", nullable = false)
    private Double latitude;

    @Column(name = "location_longitude", nullable = false)
    private Double longitude;

    @Column(name = "location_city")
    private String city;

    @Column(name = "location_state")
    private String state;

    @Column(name = "location_country")
    private String country;

    @Column(name = "location_alert_threshold")
    private Integer alertThreshold = 70;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "alert_settings_id")
    private AlertSettings alertSettings;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "alert_boundary_id")
    private AlertBoundary alertBoundary;

    @Column(name = "location_created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Location() {
    }

    public Location(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getAlertThreshold() {
        return alertThreshold;
    }

    public void setAlertThreshold(Integer alertThreshold) {
        this.alertThreshold = alertThreshold;
    }

    public AlertSettings getAlertSettings() {
        return alertSettings;
    }

    public void setAlertSettings(AlertSettings alertSettings) {
        this.alertSettings = alertSettings;
    }

    public AlertBoundary getAlertBoundary() {
        return alertBoundary;
    }

    public void setAlertBoundary(AlertBoundary alertBoundary) {
        this.alertBoundary = alertBoundary;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return name.equals(location.name) &&
                latitude.equals(location.latitude) &&
                longitude.equals(location.longitude);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + latitude.hashCode();
        result = 31 * result + longitude.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", alertThreshold=" + alertThreshold +
                '}';
    }
} 