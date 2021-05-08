package br.com.testeenginv.teste.usecase;

import br.com.testeenginv.teste.domain.Company;
import br.com.testeenginv.teste.gateway.dataprovider.CompanyDataProvider;
import lombok.AllArgsConstructor;

import javax.inject.Named;
import java.util.List;

@AllArgsConstructor
@Named
public class SearchCompanyByCodigo {

    private final CompanyDataProvider companyDataProvider;

    public List<Company> execute(String codigo){
        return companyDataProvider.findByContainingCodigo(codigo);
    }


}
