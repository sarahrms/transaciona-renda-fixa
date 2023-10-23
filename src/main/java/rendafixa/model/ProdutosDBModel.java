package rendafixa.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.io.Serializable;

@Introspected
@DynamoDbBean
public class ProdutosDBModel implements Serializable {

    @NonNull
    @NotBlank
    private String codigoProduto;

    @NonNull
    @NotBlank
    private String tipoProduto;

    @NonNull
    @NotBlank
    private String nomeProduto;

    public ProdutosDBModel(String codigoProduto, String tipoProduto, String nomeProduto) {
        this.codigoProduto = codigoProduto;
        this.tipoProduto = tipoProduto;
        this.nomeProduto = nomeProduto;
    }

    public ProdutosDBModel() {
    }

    @DynamoDbPartitionKey
    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    @DynamoDbSortKey
    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}
