package com.juri.TypingGameWorld.Member.business.router

import com.juri.TypingGameWorld.Member.business.handler.MemberHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class MemberRouter(private val memberHandler: MemberHandler) {

    @Bean
    fun route(): RouterFunction<ServerResponse> =
        RouterFunctions.route(
            RequestPredicates.POST("/members").and(
                RequestPredicates.accept(MediaType.APPLICATION_JSON)
            ),
            memberHandler::saveMember
        ).andRoute(
            RequestPredicates.DELETE("/members/{memberId}").and(
                RequestPredicates.accept(MediaType.APPLICATION_JSON)
            ),
            memberHandler::deleteMember
        ).andRoute(
            RequestPredicates.GET("/members/{memberId}").and(
                RequestPredicates.accept(MediaType.APPLICATION_JSON)
            ),
            memberHandler::findMemberByMemberId
        ).andRoute(
            RequestPredicates.GET("/members/{bestGame}").and(
                RequestPredicates.accept(MediaType.APPLICATION_JSON)
            ),
            memberHandler::findMembersByBestGame
        ).andRoute(
            RequestPredicates.PUT("/members/{memberId}").and(
                RequestPredicates.accept(MediaType.APPLICATION_JSON)
            ),
            memberHandler::updateBestGame
        ).andRoute(
            RequestPredicates.DELETE("/members/{memberId}").and(
                RequestPredicates.accept(MediaType.APPLICATION_JSON)
            ),
            memberHandler::deleteBestGame
        )

}