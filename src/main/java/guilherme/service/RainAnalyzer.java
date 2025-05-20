package guilherme.service;

import org.springframework.stereotype.Service;

//analise a probabilidade de chover, definida em application.yml
@Service
public class RainAnalyzer {
    public boolean shouldAlert(double probability, int threshold) {
        return probability >= threshold;
    }
}
