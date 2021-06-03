package br.com.zupedu.lucasmiguins.proposta.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiHealthCheck implements HealthIndicator {

    @Override
    public Health health() {

        Map<String, Object> details = new HashMap<>();

        try {

            details.put("descrição", "Health check da API");
            return Health.up().withDetails(details).build();
        } catch (Exception e) {
            details.put("descrição", e.getMessage());
            return Health.down().withDetails(details).build();
        }
    }
}
