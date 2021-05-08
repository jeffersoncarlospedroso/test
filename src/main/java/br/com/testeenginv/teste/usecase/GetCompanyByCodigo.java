package br.com.testeenginv.teste.usecase;

import br.com.testeenginv.teste.domain.Company;
import br.com.testeenginv.teste.exception.CompanyNotFound;
import br.com.testeenginv.teste.gateway.dataprovider.CompanyDataProvider;
import lombok.AllArgsConstructor;

import javax.inject.Named;

@AllArgsConstructor
@Named
public class GetCompanyByCodigo {

    private final CompanyDataProvider companyDataProvider;

    public Company execute(final String codigo) {
        return companyDataProvider.getByCodigo(codigo)
                .orElseThrow(CompanyNotFound::new);
    }

}
