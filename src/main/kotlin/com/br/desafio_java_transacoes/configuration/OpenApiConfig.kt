package com.br.desafio_java_transacoes.configuration

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "Transaction API",
        version = "1.0",
        description = "API to register and consult transactions"
    )
)
class OpenApiConfig