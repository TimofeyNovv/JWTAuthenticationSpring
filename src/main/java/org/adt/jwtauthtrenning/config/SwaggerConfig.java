package org.adt.jwtauthtrenning.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(
        name = "jwtAuth",
        scheme = "Bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "Authorization"
)
@OpenAPIDefinition(
        info = @Info(
                title = "training jwt",
                description = "documentation for backend"
        )
)
public class SwaggerConfig {
}
