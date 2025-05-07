package guilherme.service;

public class WeatherStats {
    private final double maxProbability;
    private final double averageProbability;

    public WeatherStats(double maxProbability, double averageProbability) {
        this.maxProbability = maxProbability;
        this.averageProbability = averageProbability;
    }

    public double getMaxProbability() {
        return maxProbability;
    }

    public double getAverageProbability() {
        return averageProbability;
    }
} 