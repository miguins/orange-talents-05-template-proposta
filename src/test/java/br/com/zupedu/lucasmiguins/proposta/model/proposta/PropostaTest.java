package br.com.zupedu.lucasmiguins.proposta.model.proposta;

import br.com.zupedu.lucasmiguins.proposta.dto.proposta.NovaPropostaRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@Transactional
public class PropostaTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("Deve cadastrar uma nova Proposta")
    void deveCadastrarNovaProposta() throws Exception {

        var novaProposta = new NovaPropostaRequest("Lucas", "email@email", "Tv. Spring Boot", new BigDecimal(0), "37370205070");

        mockMvc.perform(post("/api/v1/propostas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(novaProposta)))
                .andExpect(status().isCreated());

        var propostas = em.createQuery("from Proposta p where p.email = :pEmail")
                .setParameter("pEmail", novaProposta.getEmail())
                .getResultList();

        assertTrue(propostas.size() >= 1);
    }

    private String toJson(NovaPropostaRequest proposta) throws JsonProcessingException {
        return objectMapper.writeValueAsString(proposta);
    }
}
