package br.com.testeenginv.teste.gateway.dataprovider.impl.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

    Optional<CompanyEntity> findByCodigo(String codigo);

    Optional<CompanyEntity> findByAcao(String acao);

    List<CompanyEntity> findByCodigoContainingIgnoreCase(String codigo);

    List<CompanyEntity> findByAcaoContainingIgnoreCase(String acao);

    List<CompanyEntity> findTop10ByCodigoIn(List<String> acao);
}
