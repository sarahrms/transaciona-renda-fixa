package rendafixa.repository.aplicacao;

import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;
import rendafixa.model.AplicacoesDBModel;

import java.util.List;

public interface AplicacoesRepository {

    @NonNull
    List<AplicacoesDBModel> findByContaCorrente(@NonNull @NotBlank String contaCorrente);
}
