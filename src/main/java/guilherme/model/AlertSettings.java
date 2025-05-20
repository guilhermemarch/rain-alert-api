package guilherme.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "alert_settings")
public class AlertSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalTime startTime = LocalTime.of(8, 0);

    @Column(name = "end_time")
    private LocalTime endTime = LocalTime.of(20, 0);

    @Column(name = "check_interval_minutes")
    private Integer checkIntervalMinutes = 30;

    @Column(name = "is_active")
    private boolean active = true;

    public AlertSettings() {
    }

    public AlertSettings(LocalTime startTime, LocalTime endTime, Integer checkIntervalMinutes) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.checkIntervalMinutes = checkIntervalMinutes;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Integer getCheckIntervalMinutes() {
        return checkIntervalMinutes;
    }

    public void setCheckIntervalMinutes(Integer checkIntervalMinutes) {
        this.checkIntervalMinutes = checkIntervalMinutes;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertSettings that = (AlertSettings) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "AlertSettings{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", checkIntervalMinutes=" + checkIntervalMinutes +
                ", active=" + active +
                '}';
    }
} 