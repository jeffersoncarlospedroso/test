package br.com.testeenginv.teste.usecase;


import br.com.testeenginv.teste.domain.Company;
import br.com.testeenginv.teste.gateway.dataprovider.CompanyDataProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Named
public class ImportCompanies {

    private final CompanyDataProvider companyDataProvider;

    public List<String> execute(final List<Company> listCompanies) {
        List<String> response = new ArrayList<>();

        listCompanies.forEach(company -> {
            Company companyResponse = companyDataProvider.save(company);
            response.add(companyResponse.getCodigo() + " success");
        });
        return response;
    }

}
