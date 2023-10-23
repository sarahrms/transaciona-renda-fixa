package rendafixa.controller;

import io.micronaut.http.annotation.Controller;
import micronaut.api.ProdutosApi;
import micronaut.model.ProdutoResponseList;
import rendafixa.service.ProdutosService;

@Controller("consulta-renda-fixa/api")
public class ProdutosApiController implements ProdutosApi {

    private final ProdutosService produtosService;

    public ProdutosApiController(ProdutosService produtosService) {
        this.produtosService = produtosService;
    }

    @Override
    public ProdutoResponseList listarProdutos() {
        ProdutoResponseList produtoResponseList = new ProdutoResponseList();
        produtoResponseList.setList(produtosService.listarProdutos());
        return produtoResponseList;
    }
}