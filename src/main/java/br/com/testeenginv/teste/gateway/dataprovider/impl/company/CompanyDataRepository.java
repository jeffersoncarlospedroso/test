package br.com.testeenginv.teste.gateway.dataprovider.impl.company;

import br.com.testeenginv.teste.domain.Company;
import br.com.testeenginv.teste.gateway.dataprovider.CompanyDataProvider;
import lombok.AllArgsConstructor;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Named
public class CompanyDataRepository implements CompanyDataProvider {

    final private CompanyRepository companyRepository;

    @Override
    public Company save(Company company) {
        final CompanyEntity entity = CompanyEntity.builder()
                .codigo(company.getCodigo())
                .acao(company.getAcao())
                .quantidadeTeorica(company.getQuantidadeTeorica())
                .participacao(company.getParticipacao())
                .build();
        return companyRepository.save(entity).toEntity();
    }

    @Override
    public Optional<Company> getByCodigo(String codigo) {
        return companyRepository.findByCodigo(codigo).map(CompanyEntity::toEntity);
    }

    @Override
    public List<Company> findByContainingCodigo(String codigo) {
        return companyRepository.findByCodigoContainingIgnoreCase(codigo).stream().map(companyEntity -> companyEntity.toEntity()).collect(Collectors.toList());
    }

    @Override
    public Optional<Company> getByAcao(String acao) {
        return companyRepository.findByAcao(acao).map(CompanyEntity::toEntity);
    }

    @Override
    public List<Company> findByContainingAcao(String acao) {
        return companyRepository.findByAcaoContainingIgnoreCase(acao).stream().map(companyEntity -> companyEntity.toEntity()).collect(Collectors.toList());
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll().stream().map(companyEntity -> companyEntity.toEntity()).collect(Collectors.toList());
    }

    @Override
    public void deleteByCodigo(String codigo) {
        companyRepository.deleteById(codigo);
    }

    @Override
    public List<Company> findByCodigoIn(List<String> codigo) {
        return companyRepository.findTop10ByCodigoIn(codigo).stream().map(
                companyEntity -> companyEntity.toEntity()).collect(Collectors.toList());
    }
}
