package br.com.testeenginv.teste.gateway.httpin.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyDTO {

    @NotBlank
    private String codigo;

    @NotBlank
    private String acao;

    @NotNull
    private Long quantidadeTeorica;

    @NotNull
    private Double participacao;

}
