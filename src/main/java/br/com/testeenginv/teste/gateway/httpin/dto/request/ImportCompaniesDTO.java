package br.com.testeenginv.teste.gateway.httpin.dto.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportCompaniesDTO {

    @NotNull
    private MultipartFile listCompanies;

}
