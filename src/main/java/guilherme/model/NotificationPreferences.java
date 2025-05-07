package guilherme.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class NotificationPreferences {

    private boolean emailEnabled = true;
    private boolean smsEnabled = false;
    private boolean pushEnabled = false;
    private int rainThreshold = 70; //probabilidade de chuva padrao
    private String preferredNotificationTime = "08:00"; //horario padrao de notificacao
    private boolean dailySummary = true;

    public NotificationPreferences() {
    }

    public NotificationPreferences(boolean emailEnabled, boolean smsEnabled, int rainThreshold) {
        this.emailEnabled = emailEnabled;
        this.smsEnabled = smsEnabled;
        this.rainThreshold = rainThreshold;
    }

    public boolean isEmailEnabled() {
        return emailEnabled;
    }

    public void setEmailEnabled(boolean emailEnabled) {
        this.emailEnabled = emailEnabled;
    }

    public boolean isSmsEnabled() {
        return smsEnabled;
    }

    public void setSmsEnabled(boolean smsEnabled) {
        this.smsEnabled = smsEnabled;
    }

    public boolean isPushEnabled() {
        return pushEnabled;
    }

    public void setPushEnabled(boolean pushEnabled) {
        this.pushEnabled = pushEnabled;
    }

    public int getRainThreshold() {
        return rainThreshold;
    }

    public void setRainThreshold(int rainThreshold) {
        this.rainThreshold = rainThreshold;
    }

    public String getPreferredNotificationTime() {
        return preferredNotificationTime;
    }

    public void setPreferredNotificationTime(String preferredNotificationTime) {
        this.preferredNotificationTime = preferredNotificationTime;
    }

    public boolean isDailySummary() {
        return dailySummary;
    }

    public void setDailySummary(boolean dailySummary) {
        this.dailySummary = dailySummary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationPreferences that = (NotificationPreferences) o;
        return emailEnabled == that.emailEnabled &&
                smsEnabled == that.smsEnabled &&
                pushEnabled == that.pushEnabled &&
                rainThreshold == that.rainThreshold &&
                dailySummary == that.dailySummary &&
                preferredNotificationTime.equals(that.preferredNotificationTime);
    }

    @Override
    public int hashCode() {
        int result = (emailEnabled ? 1 : 0);
        result = 31 * result + (smsEnabled ? 1 : 0);
        result = 31 * result + (pushEnabled ? 1 : 0);
        result = 31 * result + rainThreshold;
        result = 31 * result + preferredNotificationTime.hashCode();
        result = 31 * result + (dailySummary ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NotificationPreferences{" +
                "emailEnabled=" + emailEnabled +
                ", smsEnabled=" + smsEnabled +
                ", pushEnabled=" + pushEnabled +
                ", rainThreshold=" + rainThreshold +
                ", preferredNotificationTime='" + preferredNotificationTime + '\'' +
                ", dailySummary=" + dailySummary +
                '}';
    }
} 