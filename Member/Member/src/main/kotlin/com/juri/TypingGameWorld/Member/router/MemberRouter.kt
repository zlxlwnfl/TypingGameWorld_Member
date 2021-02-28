package com.juri.TypingGameWorld.Member.router

import com.juri.TypingGameWorld.Member.handler.MemberHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
open class MemberRouter(private val memberHandler: MemberHandler) {

    @Bean
    fun route(): RouterFunction<ServerResponse> =
        RouterFunctions.route(
            RequestPredicates.POST("/join").and(
                RequestPredicates.accept(MediaType.APPLICATION_JSON)
            ),
            memberHandler::join
        ).andRoute(
            RequestPredicates.POST("/login").and(
                RequestPredicates.accept(MediaType.APPLICATION_JSON)
            ),
            memberHandler::login
        ).andRoute(
            RequestPredicates.GET("/check/{id}").and(
                RequestPredicates.accept(MediaType.APPLICATION_JSON)
            ),
            memberHandler::duplicateIdCheck
        )

}