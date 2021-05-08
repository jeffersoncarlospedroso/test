package br.com.testeenginv.teste.usecase;

import br.com.testeenginv.teste.domain.Company;
import br.com.testeenginv.teste.exception.CompanyNotFound;
import br.com.testeenginv.teste.gateway.dataprovider.CompanyDataProvider;
import lombok.AllArgsConstructor;

import javax.inject.Named;

@AllArgsConstructor
@Named
public class GetCompanyByAcao {

    private final CompanyDataProvider companyDataProvider;

    public Company execute(final String acao) {
        return companyDataProvider.getByAcao(acao)
                .orElseThrow(CompanyNotFound::new);
    }

}
