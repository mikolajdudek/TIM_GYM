package com.example.gym.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "GYM API", version = "1.0", description = "Dokumentacja GYM API "),
        security = { @SecurityRequirement(name = "clientCredentials"), @SecurityRequirement(name = "oauth2"), @SecurityRequirement(name = "bearerAuth") })
public class OpenApiConfig {

    public static final String SCHEME_YEARMONTH = "YearMonth";
    public static final String SCHEME_YEAR = "Year";
    public static final String SCHEME_LOCALDATE = "LocalDate";
    private static final String STRING_TYPE = "string";

    @Bean
    public OpenApiCustomiser customizeOpenApi(OAuth2ResourceServerProperties props) {
        return openApi -> openApi.getComponents()
                .addSchemas(SCHEME_YEARMONTH, new Schema<>().name(SCHEME_YEARMONTH).type(STRING_TYPE).pattern("^\\d{4}-\\d{2}$").example("2020-09"))
                .addSchemas(SCHEME_YEAR, new Schema<>().name(SCHEME_YEAR).type(STRING_TYPE).pattern("^\\d{4}$").example("2020"))
                .addSchemas(SCHEME_LOCALDATE, new Schema<>().name(SCHEME_LOCALDATE).type(STRING_TYPE).format("date").pattern("^\\d{4}-\\d{2}-\\d{2}$").example("2020-09-01"))
                .addSecuritySchemes("clientCredentials", new SecurityScheme().name("clientCredentials").type(SecurityScheme.Type.OAUTH2).flows(new OAuthFlows()
                        .clientCredentials(new OAuthFlow().tokenUrl(props.getJwt().getIssuerUri() + "/protocol/openid-connect/token"))))
                .addSecuritySchemes("oauth2", new SecurityScheme().name("oauth2").type(SecurityScheme.Type.OAUTH2).flows(new OAuthFlows()
                        .password(new OAuthFlow().tokenUrl(props.getJwt().getIssuerUri() + "/protocol/openid-connect/token"))))
                .addSecuritySchemes("bearerAuth", new SecurityScheme().name("bearerAuth").type(SecurityScheme.Type.HTTP).bearerFormat("JWT").scheme("bearer"));
    }
}