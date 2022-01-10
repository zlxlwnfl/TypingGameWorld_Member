package com.juri.TypingGameWorld.Member

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class MemberHandlerTests {
/*
    @Autowired
    lateinit var webTestClient: WebTestClient
    @MockBean
    lateinit var memberService: MemberService
    private val juriMember: Member = Member(memberId = "juri", memberPassword = "1")

    @Test
    fun testValidJoin() {
        Mockito.`when`(memberService.saveMember(juriMember))
            .thenReturn(Mono.just(juriMember))

        webTestClient.post()
            .uri("/join")
            .bodyValue(juriMember)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isAccepted
    }

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