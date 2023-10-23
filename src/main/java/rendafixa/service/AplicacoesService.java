package rendafixa.service;

import jakarta.inject.Singleton;
import micronaut.model.AplicacaoResponse;
import micronaut.model.AplicacaoResponseAplicacoesInner;
import rendafixa.model.AplicacaoDBModel;
import rendafixa.model.AplicacoesDBModel;
import rendafixa.repository.aplicacao.AplicacoesDynamoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class AplicacoesService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final AplicacoesDynamoRepository aplicacoesDynamoRepository;

    public AplicacoesService(AplicacoesDynamoRepository aplicacoesDynamoRepositoryParam) {
        this.aplicacoesDynamoRepository = aplicacoesDynamoRepositoryParam;
    }

    public List<AplicacaoResponse> listarAplicacoes(String contaCorrente) {
        List<AplicacaoResponse> aplicacaoResponseList = new ArrayList<>();

        List<AplicacoesDBModel> aplicacoesDBModelList = aplicacoesDynamoRepository.findByContaCorrente(contaCorrente);

        aplicacoesDBModelList.stream().forEach(aplicacoesDBModel -> {
            List<AplicacaoDBModel> aplicacoes = aplicacoesDBModel.getAplicacoes();

            AplicacaoResponse aplicacaoResponse = new AplicacaoResponse();
            aplicacaoResponse.setCodigoProduto(aplicacoesDBModel.getCodigoProduto());
            aplicacaoResponse.setAplicacoes(new ArrayList<>());

            aplicacoes.stream().forEach(aplicacaoDBModel -> {
                micronaut.model.AplicacaoResponseAplicacoesInner aplicacaoResponseAplicacoesInner = new AplicacaoResponseAplicacoesInner();
                aplicacaoResponseAplicacoesInner.setData(LocalDate.parse(aplicacaoDBModel.getDataAplicacao(), formatter));
                aplicacaoResponseAplicacoesInner.setValor(aplicacaoDBModel.getValor().doubleValue());

                aplicacaoResponse.getAplicacoes().add(aplicacaoResponseAplicacoesInner);
            });
            aplicacaoResponseList.add(aplicacaoResponse);
        });
        return aplicacaoResponseList;
    }
}
