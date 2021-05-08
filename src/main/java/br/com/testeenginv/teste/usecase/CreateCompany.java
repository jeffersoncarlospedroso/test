package br.com.testeenginv.teste.usecase;


import br.com.testeenginv.teste.domain.Company;
import br.com.testeenginv.teste.exception.CompanyNotUnique;
import br.com.testeenginv.teste.gateway.dataprovider.CompanyDataProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Slf4j
@AllArgsConstructor
@Named
public class CreateCompany {

    private final CompanyDataProvider companyDataProvider;

    public Company execute(final Company company) {
        validateUniqueCodigo(company.getCodigo());
        return companyDataProvider.save(company);
    }

    private void validateUniqueCodigo(final String codigo) {
        companyDataProvider.getByCodigo(codigo)
                .ifPresent(company -> {
                    log.debug("codigo já registrado");
                    throw new CompanyNotUnique();
                });
    }

}
