package com.juri.TypingGameWorld.Member

import com.juri.TypingGameWorld.Member.domain.Member
import com.juri.TypingGameWorld.Member.repository.MemberRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier

@DataMongoTest
class MemberRepoTests {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @BeforeEach
    fun init() {
        memberRepository.deleteAll().block()
        memberRepository.saveAll(
            mutableListOf(
                Member(memberId = "juri", memberPassword = "1"),
                Member(memberId = "sawual", memberPassword = "2"),
                Member(memberId = "hyuk", memberPassword = "3")
            )
        ).blockLast()
    }

    @Test
    fun repoFindByMemberIdSuccessTest() {
        StepVerifier.create(memberRepository.findByMemberId("juri"))
            .assertNext {
                assert(it.memberId == "juri")
                assert(it.memberPassword == "1")
            }
            .verifyComplete()
    }

    @Test
    fun repoFindByMemberIdFailureTest() {
        StepVerifier.create(memberRepository.findByMemberId("minsu"))
            .expectNextCount(0)
            .verifyComplete()
    }

    @Test
    fun repoFindByMemberIdAndMemberPasswordSuccessTest() {
        StepVerifier.create(memberRepository.findByMemberIdAndMemberPassword("juri", "1"))
            .assertNext {
                assert(it.memberId == "juri")
                assert(it.memberPassword == "1")
            }
            .verifyComplete()
    }

    @Test
    fun repoFindByMemberIdAndMemberPasswordFilureTest() {
        StepVerifier.create(memberRepository.findByMemberIdAndMemberPassword("minsu", "1"))
            .expectNextCount(0)
            .verifyComplete()
    }

}