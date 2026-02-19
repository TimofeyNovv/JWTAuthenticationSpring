package org.adt.jwtauthtrenning.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(
        name = "jwtAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "Authorization",
        scheme = "bearer"
)
@OpenAPIDefinition(
        info = @Info(
                title = "jwt training",
                description = "documentation for backend endpoints"
        )
)
public class SwaggerConfig {
}
