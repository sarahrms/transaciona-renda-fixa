package rendafixa.repository.produtos;

import io.micronaut.core.annotation.NonNull;
import rendafixa.model.ProdutosDBModel;

import java.util.List;

public interface ProdutosRepository {

    @NonNull
    List<ProdutosDBModel> findAll();
}
