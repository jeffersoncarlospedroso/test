package br.com.testeenginv.teste.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Company {
	
	private final String codigo;
	private final String acao;
	private final Long  quantidadeTeorica;
	private final Double participacao;

}
