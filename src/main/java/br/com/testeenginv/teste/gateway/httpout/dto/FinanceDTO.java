package br.com.testeenginv.teste.gateway.httpout.dto;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinanceDTO {

    private String symbol;
    private Double regularMarketPrice;
}
