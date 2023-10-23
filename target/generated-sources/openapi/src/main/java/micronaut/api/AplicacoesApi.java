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
import micronaut.model.AplicacaoResponseList;
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
@Tag(name = "Aplicacoes", description = "The Aplicacoes API")
public interface AplicacoesApi {

    /**
     * Lista de Aplicações
     * Retorna uma lista de aplicações.
     *
     * @param contaCorrente Conta Corrente do cliente (required)
     *
     * @return AplicacaoResponseList
     */
    @Operation(
        operationId = "listarAplicacoes",
        summary = "Lista de Aplicações",
        responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = AplicacaoResponseList.class))
            }),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida")
        },
        parameters = {
            @Parameter(name = "contaCorrente", description = "Conta Corrente do cliente", required = true)
        }
    )
    @Get("/aplicacoes/{contaCorrente}")
    @Produces({"application/json"})
    AplicacaoResponseList listarAplicacoes(
        @NotNull
        @PathVariable("contaCorrente")
        String contaCorrente
    );

}