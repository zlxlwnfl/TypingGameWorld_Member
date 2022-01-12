package com.juri.TypingGameWorld.Member.tests

import com.juri.TypingGameWorld.Member.business.dto.MemberDTO
import com.juri.TypingGameWorld.Member.business.service.MemberService
import com.juri.TypingGameWorld.Member.config.MockDefaultConfiguration
import com.juri.TypingGameWorld.Member.config.MockRepoConfiguration
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Import(*[
    MockDefaultConfiguration::class,
    MockRepoConfiguration::class
])
class MemberHandlerTests {

    @Autowired
    lateinit var webTestClient: WebTestClient
    @MockBean
    lateinit var memberService: MemberService

    private val MEMBER_ID: String = "test"

    private val testValidMemberRequest : MemberDTO.Request = MemberDTO.Request(
        memberId = MEMBER_ID
    )
    private val testValidMemberResponse : MemberDTO.Response = MemberDTO.Response(
        memberId = MEMBER_ID,
        bestGame = "",
        gameScores = mapOf()
    )

    @Test
    fun testSaveMemberSuccessWithValidMemberRequest() {
        Mockito.`when`(memberService.saveMember(testValidMemberRequest))
            .thenReturn(Mono.just(testValidMemberResponse))

        webTestClient.post()
            .uri("/members")
            .bodyValue(testValidMemberRequest)
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().isCreated
            .expectHeader()
            .location("/members/${MEMBER_ID}")
    }
/*
    @Test
    fun testValidLogin() {
        Mockito.`when`(memberService.findMember(juriMember))
            .thenReturn(Mono.just(juriMember))

        webTestClient.post()
            .uri("/login")
            .bodyValue(juriMember)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun testInvalidLogin() {
        Mockito.`when`(memberService.findMember(juriMember))
            .thenReturn(Mono.empty())

        webTestClient.post()
            .uri("/login")
            .bodyValue(juriMember)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound
    }

    @Test
    fun testValidIdCheck() {
        Mockito.`when`(memberService.duplicateIdCheck("juri"))
            .thenReturn(Mono.just(true))

        webTestClient.get()
            .uri("/check/juri")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody<Boolean>()
            .isEqualTo(true)
    }

    @Test
    fun testInvalidIdCheck() {
        Mockito.`when`(memberService.duplicateIdCheck("juri"))
            .thenReturn(Mono.just(false))

        webTestClient.get()
            .uri("/check/juri")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody<Boolean>()
            .isEqualTo(false)
    }
*/
}