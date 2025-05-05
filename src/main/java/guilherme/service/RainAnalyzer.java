package guilherme.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//analise a probabilidade de chover, definida em application.yml
    @Service
    public class RainAnalyzer {

        @Value("${weather.threshold}")
        private double rainThreshold;

        public boolean shouldAlert(Double rainProbability) {
            return rainProbability != null && rainProbability >= rainThreshold;
        }
    }
