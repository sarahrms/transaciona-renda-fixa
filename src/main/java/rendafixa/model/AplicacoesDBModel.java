package rendafixa.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.io.Serializable;
import java.util.List;

@Introspected
@DynamoDbBean
public class AplicacoesDBModel implements Serializable {

    @NonNull
    @NotBlank
    private String contaCorrente;

    @NonNull
    @NotBlank
    private String codigoProduto;

    @NonNull
    @NotBlank
    private List<AplicacaoDBModel> aplicacoes;

    public AplicacoesDBModel(String contaCorrente, String codigoProduto, List<AplicacaoDBModel> aplicacoes) {
        this.contaCorrente = contaCorrente;
        this.codigoProduto = codigoProduto;
        this.aplicacoes = aplicacoes;
    }

    public AplicacoesDBModel() {
    }

    @DynamoDbPartitionKey
    public String getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    @DynamoDbSortKey
    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    @DynamoDbConvertedBy(CustomAttributeConverter.class)
    public List<AplicacaoDBModel> getAplicacoes() {
        return aplicacoes;
    }

    public void setAplicacoes(List<AplicacaoDBModel> aplicacoes) {
        this.aplicacoes = aplicacoes;
    }

}