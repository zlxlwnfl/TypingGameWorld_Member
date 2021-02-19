package com.juri.TypingGameWorld.Member.handler

import com.juri.TypingGameWorld.Member.domain.Test
import com.juri.TypingGameWorld.Member.repository.TestRepository
import com.juri.TypingGameWorld.Member.service.TestService
import kotlinx.coroutines.reactive.awaitSingle
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*
import java.util.function.Function
import java.util.function.Supplier

@Component
class MemberHandler(private val testSerivce: TestService) {

    private val log = LoggerFactory.getLogger(MemberHandler::class.java)

    fun mongodbTest(request: ServerRequest): Mono<ServerResponse> {
        val result = request.bodyToMono(Test::class.java)

        return ok().contentType(MediaType.APPLICATION_JSON)
            .body(request.bodyToMono(Test::class.java)
                .flatMap(testSerivce::saveTest)
                .flatMap(testSerivce::findTest))
            .switchIfEmpty(notFound().build())
    }

}