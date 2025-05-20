package guilherme.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Alerta do Tempo")
                .version("1.0")
                .description("O objetivo da API é notificar o usuario sobre as condições do tempo, focando na chuva")
                .contact(new Contact()
                    .name("Guilherme")
                    .email("guilherme@guilhermemarschall.com.br")))
            .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
            .components(new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes("basicAuth", new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("basic")));
    }
} 