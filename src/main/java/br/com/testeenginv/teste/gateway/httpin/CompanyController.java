package br.com.testeenginv.teste.gateway.httpin;

import br.com.testeenginv.teste.gateway.httpin.converter.CompanyConverter;
import br.com.testeenginv.teste.gateway.httpin.dto.request.CreateCompanyDTO;
import br.com.testeenginv.teste.gateway.httpin.dto.request.ImportCompaniesDTO;
import br.com.testeenginv.teste.gateway.httpin.dto.response.CompanyDTO;
import br.com.testeenginv.teste.usecase.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/companies")
@AllArgsConstructor
public class CompanyController {

    private final ImportCompanies importCompanies;
    private final CreateCompany createCompany;
    private final GetAllCompanies getAllCompanies;
    private final GetCompanyByCodigo getCompanyByCodigo;
    private final SearchCompanyByCodigo searchCompanyByCodigo;
    private final GetCompanyByAcao getCompanyByAcao;
    private final SearchCompanyByAcao searchCompanyByAcao;
    private final DeleteCompany deleteCompany;
    private final GetTop10AveragePrice getTop10AveragePrice;

    @PostMapping(value = "/import")
    public ResponseEntity<List<String>> importCompanies(@ModelAttribute final ImportCompaniesDTO importCompaniesDTO) {
        List<String> response = importCompanies.execute(CompanyConverter.toListEntity(importCompaniesDTO));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@Validated @RequestBody final CreateCompanyDTO createCompanyDTO) {
        return ResponseEntity.ok(createCompany.execute(CompanyConverter.toEntity(createCompanyDTO)).getCodigo() + " criado com sucesso");
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(CompanyConverter.fromEntity(getAllCompanies.execute()));
    }

    @GetMapping(value = "/codigo/{codigo}")
    public ResponseEntity<CompanyDTO> getByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(CompanyConverter.fromEntity(getCompanyByCodigo.execute(codigo)));
    }

    @GetMapping(value = "/searchCodigo/{codigo}")
    public ResponseEntity<List<CompanyDTO>> searchByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(CompanyConverter.fromEntity(searchCompanyByCodigo.execute(codigo)));
    }

    @GetMapping(value = "/acao/{acao}")
    public ResponseEntity<CompanyDTO> getByAcao(@PathVariable String acao) {
        return ResponseEntity.ok(CompanyConverter.fromEntity(getCompanyByAcao.execute(acao)));
    }

    @GetMapping(value = "/searchAcao/{acao}")
    public ResponseEntity<List<CompanyDTO>> searchByAcao(@PathVariable String acao) {
        return ResponseEntity.ok(CompanyConverter.fromEntity(searchCompanyByAcao.execute(acao)));
    }

    @GetMapping(value = "/top10")
    public ResponseEntity<List<CompanyDTO>> getTop10() {
        return ResponseEntity.ok(CompanyConverter.fromEntity(
                getTop10AveragePrice.execute()));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable String codigo){
        deleteCompany.execute(codigo);
        return ResponseEntity.noContent().build();
    }
}
