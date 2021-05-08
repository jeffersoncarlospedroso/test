package br.com.testeenginv.teste.gateway.httpin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CompanyDTO {

    private String codigo;
    private String acao;
    private Long quantidadeTeorica;
    private Double participacao;

}
