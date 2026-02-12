package org.adt.jwtauthtrenning.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "jwtAuth",
        scheme = "Bearer",
        bearerFormat = "Authorization",
        type = SecuritySchemeType.HTTP
)
@OpenAPIDefinition(
        info = @Info(
                title = "training jwt",
                description = "documentation for back end"
        )
)
public class SwaggerConfig {
}
