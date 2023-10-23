package rendafixa.service;

import jakarta.inject.Singleton;
import micronaut.model.ProdutoResponse;
import rendafixa.model.ProdutosDBModel;
import rendafixa.repository.produtos.ProdutosDynamoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ProdutosService {

    private final ProdutosDynamoRepository produtosDynamoRepository;

    public ProdutosService(ProdutosDynamoRepository produtosDynamoRepository) {
        this.produtosDynamoRepository = produtosDynamoRepository;
    }

    public List<ProdutoResponse> listarProdutos() {
        List<ProdutosDBModel> produtosDBModelList = produtosDynamoRepository.findAll();
        return produtosDBModelList.stream()
                .map(produtosDBModel -> {
                    ProdutoResponse produtoResponse = new ProdutoResponse();
                    produtoResponse.setNomeProduto(produtosDBModel.getNomeProduto());
                    produtoResponse.setTipoProduto(produtosDBModel.getTipoProduto());
                    produtoResponse.setCodigoProduto(produtosDBModel.getCodigoProduto());
                    return produtoResponse;
                })
                .collect(Collectors.toList());
    }
}