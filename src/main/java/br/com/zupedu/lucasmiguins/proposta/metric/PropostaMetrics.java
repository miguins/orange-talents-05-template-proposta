package br.com.zupedu.lucasmiguins.proposta.metric;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class PropostaMetrics {

    private final MeterRegistry meterRegistry;

    public Counter contadorDePropostasCriadas;

    public PropostaMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.contarPropostasCriadas();
    }

    public void contarPropostasCriadas() {
        Collection<Tag> tags = new ArrayList<>();

        contadorDePropostasCriadas = this.meterRegistry.counter("proposta_criada", tags);
    }

}
