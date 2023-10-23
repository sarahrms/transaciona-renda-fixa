package rendafixa.controller;

import io.micronaut.http.annotation.Controller;
import micronaut.api.AplicacoesApi;
import micronaut.model.AplicacaoResponseList;
import rendafixa.service.AplicacoesService;

@Controller("consulta-renda-fixa/api")
public class AplicacoesApiController implements AplicacoesApi {

    private final AplicacoesService aplicacoesService;

    public AplicacoesApiController(AplicacoesService aplicacoesService) {
        this.aplicacoesService = aplicacoesService;
    }

    @Override
    public AplicacaoResponseList listarAplicacoes(String contaCorrente) {
        AplicacaoResponseList aplicacaoResponseList = new AplicacaoResponseList();
        aplicacaoResponseList.setList(aplicacoesService.listarAplicacoes(contaCorrente));
        return aplicacaoResponseList;
    }
}