package rendafixa.repository.aplicacao;

import io.micronaut.aws.sdk.v2.EnvironmentAwsCredentialsProvider;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import rendafixa.configuration.AplicacoesDynamoConfiguration;
import rendafixa.model.AplicacoesDBModel;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Requires(beans = {AplicacoesDynamoConfiguration.class})
@Singleton
@Primary
public class AplicacoesDynamoRepository implements AplicacoesRepository {

    private final DynamoDbClient dynamoDbClient;
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final DynamoDbTable<AplicacoesDBModel> dynamoDbTable;

    public AplicacoesDynamoRepository(Environment environment, AplicacoesDynamoConfiguration aplicacoesDynamoConfiguration) {
        Optional<String> secretKey = environment.get("AWS_ACCESS_KEY_ID", String.class);
        Optional<String> accessKey = environment.get("AWS_SECRET_ACCESS_KEY", String.class);

        if (!secretKey.isPresent() || !accessKey.isPresent()) {
            throw new IllegalArgumentException("Aws credentials not provided");
        }

        String tableName = aplicacoesDynamoConfiguration.getTableName();

        Region region = Region.of("us-east-2");

        this.dynamoDbClient = DynamoDbClient.builder()
                .region(region)
                .credentialsProvider(EnvironmentAwsCredentialsProvider.create(environment))
                .build();

        this.dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.dynamoDbTable = dynamoDbEnhancedClient.table(tableName, TableSchema.fromBean(AplicacoesDBModel.class));
    }

    @NonNull
    public List<AplicacoesDBModel> findByContaCorrente(@NonNull @NotBlank String contaCorrente) {
        List<AplicacoesDBModel> lista = new ArrayList<>();

        Iterator<AplicacoesDBModel> iterator = dynamoDbTable.scan().items().iterator();
        while (iterator.hasNext()) {
            AplicacoesDBModel aplicacoesDBModel = iterator.next();
            if (aplicacoesDBModel.getContaCorrente().equals(contaCorrente)) {
                lista.add(aplicacoesDBModel);
            }
        }
        return lista;
    }
}