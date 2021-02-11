package com.juri.TypingGameWorld.Member.handler

import com.juri.TypingGameWorld.Member.domain.Test
import com.juri.TypingGameWorld.Member.repository.TestRepository
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
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*
import java.util.function.Function
import java.util.function.Supplier

@Slf4j
@Component
class MemberHandler(private val testRepo: TestRepository) {

    private val log = LoggerFactory.getLogger(MemberHandler::class.java)

    fun helloWorld(request: ServerRequest): Mono<ServerResponse> {
        log.info("method before")
        val name = request.pathVariable("name")
        val message = request.pathVariable("message")
        val result = ok().contentType(MediaType.APPLICATION_JSON)
            .body(Mono.fromSupplier(Supplier { Member.memberFactory(name, message) }))
            .switchIfEmpty(notFound().build())
        log.info("method after")

        return result
    }

    fun mongodbTest(request: ServerRequest): Mono<ServerResponse> {
        log.info("method before")
        val result = request.bodyToMono(Test::class.java)
            .filter(Objects::nonNull)
            .flatMap {test ->
                log.info("저장 시도 : ${test.memberId} ${test.memberPassword}")
                testRepo.save(Test("", test.memberId, test.memberPassword))
            }.flatMap { savedTest ->
                log.info("조회 시도 : ${savedTest.memberId} ${savedTest.memberPassword}")
                testRepo.findByMemberId(savedTest.memberId).toMono()
            }.log()
        log.info("method after")

        return ok().contentType(MediaType.APPLICATION_JSON)
            .body(result)
            .switchIfEmpty(notFound().build())
    }

}

class Member(val name: String, val message: String) {

    companion object {
        fun memberFactory(name: String, message: String): Member {
            Thread.sleep(1000)
            println("create member")
            return Member(name, message)
        }
    }
}