package br.com.zupedu.lucasmiguins.proposta.dto.avisoviagem;

import br.com.zupedu.lucasmiguins.proposta.model.AvisoViagem;
import br.com.zupedu.lucasmiguins.proposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NovoAvisoViagemRequest {

    @NotBlank
    private String destino;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataTermino;

    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataInicio;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovoAvisoViagemRequest(String destino, LocalDate dataTermino, LocalDate dataInicio) {
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.dataInicio = dataInicio;
    }

    public AvisoViagem toModel(Cartao cartao, String ipCliente, String userAgenteCliente) {
        return new AvisoViagem(this.destino, this.dataTermino, this.dataInicio, ipCliente, userAgenteCliente, cartao);
    }
}
