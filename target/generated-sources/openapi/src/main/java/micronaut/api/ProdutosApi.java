/*
 * API Consulta Renda Fixa
 * Uma API para listar aplicações e produtos Renda Fixa.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package micronaut.api;


import io.micronaut.http.annotation.*;
import micronaut.invoker.annotation.HardNullable;
import io.micronaut.core.convert.format.Format;
import micronaut.model.ProdutoResponseList;
import jakarta.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Generated(value = "io.micronaut.openapi.generator.JavaMicronautServerCodegen")
@Controller
@Tag(name = "Produtos", description = "The Produtos API")
public interface ProdutosApi {

    /**
     * Lista de Produtos
     * Retorna uma lista de todos os produtos.
     *
     * @return ProdutoResponseList
     */
    @Operation(
        operationId = "listarProdutos",
        summary = "Lista de Produtos",
        responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoResponseList.class))
            }),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida")
        }
    )
    @Get("/produtos")
    @Produces({"application/json"})
    ProdutoResponseList listarProdutos();

}