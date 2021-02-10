package com.juri.TypingGameWorld.Member.handler

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import kotlin.reflect.typeOf

@Component
class MemberHandler {

    fun helloWorld(request: ServerRequest): Mono<ServerResponse> {
        return ok().contentType(MediaType.APPLICATION_JSON)
            .body<Member>(Mono.just(Member("juri", "hello world!!")))
            .switchIfEmpty(notFound().build())
    }

}

data class Member(val name: String, val message: String)