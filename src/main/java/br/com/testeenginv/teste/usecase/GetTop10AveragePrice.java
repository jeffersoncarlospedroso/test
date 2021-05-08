package br.com.testeenginv.teste.usecase;


import br.com.testeenginv.teste.domain.Company;
import br.com.testeenginv.teste.gateway.httpout.FinanceProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Named
public class GetTop10AveragePrice {

    private final FinanceProvider financeProvider;

    public List<Company> execute() {
        return financeProvider.getTop10();
    }
}
