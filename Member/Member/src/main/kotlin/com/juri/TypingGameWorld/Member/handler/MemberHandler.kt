package com.juri.TypingGameWorld.Member.handler

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Slf4j
@Component
class MemberHandler {

    private val log = LoggerFactory.getLogger(MemberHandler::class.java)

    fun helloWorld(request: ServerRequest): Mono<ServerResponse> {
        log.info("method before")
        val result = ok().contentType(MediaType.APPLICATION_JSON)
            .body<Member>(Mono.fromSupplier<Member>(Member::memberFactory))
            .switchIfEmpty(notFound().build())
        log.info("method after")
        return result
    }

}

class Member(val name: String, val message: String) {

    companion object {
        fun memberFactory(): Member {
            Thread.sleep(1000)
            println("create member")
            return Member("juri", "hello world!")
        }
    }
}