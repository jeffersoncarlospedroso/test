package br.com.testeenginv.teste.gateway.httpin.converter;

import br.com.testeenginv.teste.domain.Company;
import br.com.testeenginv.teste.gateway.httpin.dto.request.CreateCompanyDTO;
import br.com.testeenginv.teste.gateway.httpin.dto.request.ImportCompaniesDTO;
import br.com.testeenginv.teste.gateway.httpin.dto.response.CompanyDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CompanyConverter {

    public static List<Company> toListEntity(final ImportCompaniesDTO importCompaniesDTO) {

        List<Company> companyList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(importCompaniesDTO.getListCompanies().getInputStream(), "UTF-8"));
            String bufferedLine;
            while ((bufferedLine = bufferedReader.readLine()) != null) {
                String[] splitted = bufferedLine.split(";");

                companyList.add(Company.builder().codigo(splitted[0])
                        .acao(splitted[1])
                        .quantidadeTeorica(Long.parseLong(splitted[2]))
                        .participacao(Double.parseDouble(splitted[3]))
                        .build()
                );
            }
        } catch (IOException ioException) {
            log.error("Import Error: {} {}", ioException.getClass(), ioException.getMessage());

        }
        return companyList;
    }

    public static List<CompanyDTO> fromEntity(final List<Company> listCompany) {
        List<CompanyDTO> returnList = new ArrayList<>();
        listCompany.forEach(company -> returnList.add(CompanyDTO.builder()
                .codigo(company.getCodigo())
                .acao(company.getAcao())
                .quantidadeTeorica(company.getQuantidadeTeorica())
                .participacao(company.getParticipacao()).build()
        ));
        return returnList;
    }

    public static Company toEntity(final CreateCompanyDTO createCompanyDTO) {
        return Company.builder()
                .participacao(createCompanyDTO.getParticipacao())
                .quantidadeTeorica(createCompanyDTO.getQuantidadeTeorica())
                .acao(createCompanyDTO.getAcao())
                .codigo(createCompanyDTO.getCodigo())
                .build();
    }

    public static CompanyDTO fromEntity(final Company company) {
        return CompanyDTO.builder()
                .codigo(company.getCodigo())
                .acao(company.getAcao())
                .participacao(company.getParticipacao())
                .quantidadeTeorica(company.getQuantidadeTeorica())
                .build();
    }
}
