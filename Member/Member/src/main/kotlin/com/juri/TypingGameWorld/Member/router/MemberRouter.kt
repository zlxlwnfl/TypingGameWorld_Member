package com.juri.TypingGameWorld.Member.router

import com.juri.TypingGameWorld.Member.handler.MemberHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

@Configuration
open class MemberRouter(private val memberHandler: MemberHandler) {

    @Bean
    fun route(): RouterFunction<ServerResponse> =
        RouterFunctions.route(
            RequestPredicates.POST("/test").and(
                RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                memberHandler::mongodbTest
            )

}