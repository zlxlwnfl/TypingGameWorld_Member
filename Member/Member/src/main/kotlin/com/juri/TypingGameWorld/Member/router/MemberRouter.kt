package com.juri.TypingGameWorld.Member.router

import com.juri.TypingGameWorld.Member.handler.MemberHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

@Configuration
open class MemberRouter(private val memberHandler: MemberHandler) {

    @Bean
    fun route(): RouterFunction<ServerResponse> {
        return RouterFunctions.route(
            RequestPredicates.GET("/{name}/{message}").and(
                RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                memberHandler::helloWorld
            ).andRoute(
            RequestPredicates.GET("/test/{name}/{message}").and(
                RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                memberHandler::mongodbTest
            )
    }

}