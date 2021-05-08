package br.com.testeenginv.teste.gateway.dataprovider;

import br.com.testeenginv.teste.domain.Company;
import java.util.List;
import java.util.Optional;

public interface CompanyDataProvider {

    /**
     * Saves an Company.
     *
     * @param company
     * @return
     */
    Company save(final Company company);

    /**
     * Get an Company by Codigo
     *
     * @param codigo
     */
    Optional<Company> getByCodigo(final String codigo);

    /**
     * Search an Company by Codigo
     *
     * @param codigo
     */
    List<Company> findByContainingCodigo(final String codigo);

    /**
     * Get an Company by Acao
     *
     * @param acao
     */
    Optional<Company> getByAcao(final String acao);

    /**
     * Search an Company by Acao
     *
     * @param acao
     */
    List<Company> findByContainingAcao(final String acao);

    /**
     * Get all Companies
     */
    List<Company> findAll();


    /**
     * Delete an Company by Acao
     */
    void deleteByCodigo(String codigo);

    /**
     * Search top10 from list
     *
     * @param codigo
     */
    List<Company> findByCodigoIn(List<String> codigo);

}
