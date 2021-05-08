package br.com.testeenginv.teste.gateway.dataprovider.impl.company;


import br.com.testeenginv.teste.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class CompanyEntity {

    @Id
    @Column(name = "codigo")
    private String codigo;

    @NotNull
    @Column(name = "acao")
    private String acao;

    @NotNull
    @Column(name = "quantidade_teorica")
    private Long quantidadeTeorica;

    @NotNull
    @Column(name = "participacao")
    private Double participacao;

    public Company toEntity() {
        return Company.builder()
                .codigo(this.codigo)
                .acao(this.acao)
                .quantidadeTeorica(this.quantidadeTeorica)
                .participacao(this.participacao)
                .build();
    }

}
