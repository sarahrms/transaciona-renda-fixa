package rendafixa.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import jakarta.validation.constraints.NotBlank;

@Requires(property = "dynamodb.aplicacoes.table-name")
@ConfigurationProperties("dynamodb.aplicacoes")
public interface AplicacoesDynamoConfiguration {
    @NotBlank
    String getTableName();
}