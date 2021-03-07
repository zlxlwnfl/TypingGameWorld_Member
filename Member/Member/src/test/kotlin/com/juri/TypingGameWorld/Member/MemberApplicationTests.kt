package com.juri.TypingGameWorld.Member

import com.juri.TypingGameWorld.Member.domain.Member
import com.juri.TypingGameWorld.Member.handler.MemberHandler
import com.juri.TypingGameWorld.Member.repository.MemberRepository
import com.juri.TypingGameWorld.Member.router.MemberRouter
import com.juri.TypingGameWorld.Member.service.MemberService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.data.mongodb.config.MongoConfigurationSupport
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.convert.MongoConverter
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Mono

@SpringBootTest
@AutoConfigureWebTestClient
@AutoConfigureDataMongo
class MemberApplicationTests {

    @Autowired lateinit var webTestClient: WebTestClient
    @MockBean lateinit var memberService: MemberService
    private val member: Member = Member(memberId = "juri", memberPassword = "1")

    @Test
    fun joinSuccessTest() {
        Mockito.`when`(memberService.saveMember(member)).thenReturn(Mono.defer { Mono.just(member) })

        webTestClient.post()
            .uri("/join")
            .bodyValue(member)
            .accept(MediaType.ALL)
            .header("Content-Type", "application/json;charset=UTF-8")
            .exchange()
            .expectStatus().isAccepted
    }

    @Test
    fun loginSuccessTest() {
        Mockito.`when`(memberService.findMember(member)).thenReturn(Mono.just(member))

        webTestClient.post()
            .uri("/login")
            .bodyValue(member)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun loginFailureTest() {
        Mockito.`when`(memberService.findMember(member)).thenReturn(Mono.empty())

        webTestClient.post()
            .uri("/login")
            .bodyValue(member)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound
    }

    @Test
    fun idCheckSuccessTest() {
        Mockito.`when`(memberService.duplicateIdCheck("juri")).thenReturn(Mono.just(true))

        webTestClient.get()
            .uri("/check/juri")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody<Boolean>()
            .isEqualTo(true)
    }

    @Test
    fun idCheckFailureTest() {
        Mockito.`when`(memberService.duplicateIdCheck("juri")).thenReturn(Mono.just(false))

        webTestClient.get()
            .uri("/check/juri")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody<Boolean>()
            .isEqualTo(false)
    }

}