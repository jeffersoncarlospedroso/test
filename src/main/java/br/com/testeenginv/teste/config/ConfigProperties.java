package br.com.testeenginv.teste.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.inject.Named;

@Getter
@Setter
@ConfigurationProperties(prefix = "config")
@Named
public class ConfigProperties {
    private String xRapidKey;

}
