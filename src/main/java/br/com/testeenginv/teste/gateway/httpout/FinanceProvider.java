package br.com.testeenginv.teste.gateway.httpout;

import br.com.testeenginv.teste.domain.Company;

import java.util.List;

public interface FinanceProvider {

    /**
     * Get prices
     */
    List<Company> getTop10();
}
