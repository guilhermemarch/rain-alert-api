package guilherme.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alert_boundaries")
public class AlertBoundary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_latitude")
    private Double minLatitude;

    @Column(name = "max_latitude")
    private Double maxLatitude;

    @Column(name = "min_longitude")
    private Double minLongitude;

    @Column(name = "max_longitude")
    private Double maxLongitude;

    @Column(name = "radius_km")
    private Double radiusKm;

    @Column(name = "polygon_points", columnDefinition = "jsonb")
    private String polygonPoints;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum BoundaryType {
        CIRCLE,
        POLYGON
    }

    public AlertBoundary() {
    }

    public AlertBoundary(Double minLatitude, Double maxLatitude, Double minLongitude, Double maxLongitude, Double radiusKm) {
        this.minLatitude = minLatitude;
        this.maxLatitude = maxLatitude;
        this.minLongitude = minLongitude;
        this.maxLongitude = maxLongitude;
        this.radiusKm = radiusKm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMinLatitude() {
        return minLatitude;
    }

    public void setMinLatitude(Double minLatitude) {
        this.minLatitude = minLatitude;
    }

    public Double getMaxLatitude() {
        return maxLatitude;
    }

    public void setMaxLatitude(Double maxLatitude) {
        this.maxLatitude = maxLatitude;
    }

    public Double getMinLongitude() {
        return minLongitude;
    }

    public void setMinLongitude(Double minLongitude) {
        this.minLongitude = minLongitude;
    }

    public Double getMaxLongitude() {
        return maxLongitude;
    }

    public void setMaxLongitude(Double maxLongitude) {
        this.maxLongitude = maxLongitude;
    }

    public Double getRadiusKm() {
        return radiusKm;
    }

    public void setRadiusKm(Double radiusKm) {
        this.radiusKm = radiusKm;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertBoundary that = (AlertBoundary) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "AlertBoundary{" +
                "id=" + id +
                ", minLatitude=" + minLatitude +
                ", maxLatitude=" + maxLatitude +
                ", minLongitude=" + minLongitude +
                ", maxLongitude=" + maxLongitude +
                ", radiusKm=" + radiusKm +
                '}';
    }
} 