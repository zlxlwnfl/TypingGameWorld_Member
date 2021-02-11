package com.juri.TypingGameWorld.Member.handler

import com.juri.TypingGameWorld.Member.domain.Test
import com.juri.TypingGameWorld.Member.repository.TestRepository
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
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
        val result = Mono.fromRunnable<Void>(
            Runnable {
                testRepo.save(Test("",
                    request.pathVariable("name"),
                    request.pathVariable("message"))).subscribe()
                println("몽고디비에 데이터 삽입 완료")
            }).then(
            testRepo.findByName(request.pathVariable("name")).collectList()
        )
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