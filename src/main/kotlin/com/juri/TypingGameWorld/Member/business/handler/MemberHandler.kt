package com.juri.TypingGameWorld.Member.business.handler

import com.juri.TypingGameWorld.Member.business.dto.MemberDTO
import com.juri.TypingGameWorld.Member.business.service.MemberService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI

@Component
class MemberHandler(private val memberSerivce: MemberService) {
    
    fun saveMember(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(MemberDTO.Request::class.java)
            .flatMap(memberSerivce::saveMember)
            .flatMap {
                ServerResponse.created(URI.create("/members/${it.memberId}"))
                    .build()
            }

    fun deleteMember(request: ServerRequest): Mono<ServerResponse> {
        return Mono.empty()
    }

    fun findMemberByMemberId(request: ServerRequest): Mono<ServerResponse> {
        return Mono.empty()
    }

    fun findMembersByBestGame(request: ServerRequest): Mono<ServerResponse> {
        return Mono.empty()
    }

    fun updateBestGame(request: ServerRequest): Mono<ServerResponse> {
        return Mono.empty()
    }

    fun deleteBestGame(request: ServerRequest): Mono<ServerResponse> {
        return Mono.empty()
    }

}