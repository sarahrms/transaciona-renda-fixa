package rendafixa.model;

import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;

public class AplicacaoDBModel implements Serializable {

    @NonNull
    @NotBlank
    private String dataAplicacao;

    @NonNull
    @NotBlank
    private BigDecimal valor;

    public AplicacaoDBModel(String dataAplicacao, BigDecimal valor) {
        this.dataAplicacao = dataAplicacao;
        this.valor = valor;
    }

    public AplicacaoDBModel() {
    }

    public String getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(String dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}