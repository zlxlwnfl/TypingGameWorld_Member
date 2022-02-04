package com.juri.TypingGameWorld.Member.business.handler

import com.juri.TypingGameWorld.Member.business.dto.MemberDTO
import com.juri.TypingGameWorld.Member.business.service.MemberService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.net.URI

@Component
class MemberHandler(private val memberSerivce: MemberService) {
    
    fun saveMember(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(MemberDTO.Request::class.java)
            .flatMap(memberSerivce::saveMember)
            .flatMap {
                ServerResponse.created(URI.create("/members/${it.memberId}"))
                    .body(BodyInserters.fromValue(it))
            }

    fun deleteMember(request: ServerRequest): Mono<ServerResponse> =
        request.pathVariable("memberId").toMono()
            .flatMap(memberSerivce::deleteMember)
            .flatMap {
                ServerResponse.ok()
                    .build()
            }

    fun findMemberByMemberId(request: ServerRequest): Mono<ServerResponse> =
        request.pathVariable("memberId").toMono()
            .flatMap(memberSerivce::findMemberByMemberId)
            .flatMap {
                ServerResponse.ok()
                    .body(BodyInserters.fromValue(it))
            }

    fun findMembersByBestGame(request: ServerRequest): Mono<ServerResponse> =
        request.pathVariable("bestGame").toMono()
            .flatMap(memberSerivce::findMembersByBestGame)
            .flatMap {
                ServerResponse.ok()
                    .body(BodyInserters.fromValue(it))
            }

    fun updateBestGame(request: ServerRequest): Mono<ServerResponse> {
        val memberId = request.pathVariable("memberId")
        val bestGame = request.queryParam("bestGame").get()

        return Mono.defer {
                memberSerivce.updateBestGame(memberId, bestGame)
            }
            .flatMap {
                ServerResponse.ok()
                    .body(BodyInserters.fromValue(it))
            }
    }

    fun deleteBestGame(request: ServerRequest): Mono<ServerResponse> =
        request.pathVariable("memberId").toMono()
            .flatMap(memberSerivce::deleteBestGame)
            .flatMap {
                ServerResponse.ok()
                    .build()
            }

}