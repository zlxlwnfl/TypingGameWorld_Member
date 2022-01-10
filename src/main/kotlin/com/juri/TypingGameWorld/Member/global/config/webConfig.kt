package com.juri.TypingGameWorld.Member.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableWebFlux
class webConfig: WebFluxConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        super.addCorsMappings(registry)

        registry.addMapping("/**")
            .allowedOrigins("*")
    }
}