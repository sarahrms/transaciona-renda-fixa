package rendafixa.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import jakarta.validation.constraints.NotBlank;

@Requires(property = "dynamodb.produtos.table-name")
@ConfigurationProperties("dynamodb.produtos")
public interface ProdutosDynamoConfiguration {
    @NotBlank
    String getTableName();
}