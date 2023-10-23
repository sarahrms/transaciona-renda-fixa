package rendafixa.repository.produtos;

import io.micronaut.aws.sdk.v2.EnvironmentAwsCredentialsProvider;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import jakarta.inject.Singleton;
import rendafixa.configuration.AplicacoesDynamoConfiguration;
import rendafixa.configuration.ProdutosDynamoConfiguration;
import rendafixa.model.ProdutosDBModel;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Requires(beans = {ProdutosDynamoConfiguration.class})
@Singleton
@Primary
public class ProdutosDynamoRepository implements ProdutosRepository {

    private final DynamoDbClient dynamoDbClient;
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final DynamoDbTable<ProdutosDBModel> dynamoDbTable;

    public ProdutosDynamoRepository(Environment environment, ProdutosDynamoConfiguration produtosDynamoConfiguration) {
        Optional<String> secretKey = environment.get("AWS_ACCESS_KEY_ID", String.class);
        Optional<String> accessKey = environment.get("AWS_SECRET_ACCESS_KEY", String.class);

        if (!secretKey.isPresent() || !accessKey.isPresent()) {
            throw new IllegalArgumentException("Aws credentials not provided");
        }

        String tableName = produtosDynamoConfiguration.getTableName();

        Region region = Region.of("us-east-2");

        this.dynamoDbClient = DynamoDbClient.builder()
                .region(region)
                .credentialsProvider(EnvironmentAwsCredentialsProvider.create(environment))
                .build();

        this.dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.dynamoDbTable = dynamoDbEnhancedClient.table(tableName, TableSchema.fromBean(ProdutosDBModel.class));
    }

    @Override
    public List<ProdutosDBModel> findAll() {
        List<ProdutosDBModel> lista = new ArrayList<>();

        Iterator<ProdutosDBModel> iterator = dynamoDbTable.scan().items().iterator();
        while (iterator.hasNext()) {
            ProdutosDBModel produtosDBModel = iterator.next();
            lista.add(produtosDBModel);
        }
        return lista;
    }
}
