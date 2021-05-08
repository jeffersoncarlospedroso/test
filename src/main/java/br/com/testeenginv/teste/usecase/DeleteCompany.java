package br.com.testeenginv.teste.usecase;

import br.com.testeenginv.teste.domain.Company;
import br.com.testeenginv.teste.gateway.dataprovider.CompanyDataProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Slf4j
@AllArgsConstructor
@Named
public class DeleteCompany {

    private final CompanyDataProvider companyDataProvider;
    private final GetCompanyByCodigo getCompanyByCodigo;

    public void execute(final String codigo) {

        getCompanyByCodigo.execute(codigo);
        companyDataProvider.deleteByCodigo(codigo);
    }

}
