package com.juri.TypingGameWorld.Member

import com.juri.TypingGameWorld.Member.domain.Member
import com.juri.TypingGameWorld.Member.repository.MemberRepository
import com.juri.TypingGameWorld.Member.service.MemberService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
class MemberServiceTests {

    @Autowired
    lateinit var memberService: MemberService

    @MockBean
    lateinit var memberRepo: MemberRepository
    private val juriMember: Member = Member(memberId = "juri", memberPassword = "1")

    @Test
    fun saveMemberSuccessTest() {
        Mockito.`when`(memberRepo.save(juriMember)).thenReturn(Mono.just(juriMember))

        StepVerifier.create(memberService.saveMember(juriMember))
            .assertNext {
                assert(it.memberId == "juri")
                assert(it.memberPassword == "1")
            }
            .verifyComplete()
    }

    @Test
    fun findMemberSuccessTest() {
        Mockito.`when`(memberRepo.findByMemberIdAndMemberPassword("juri", "1")).thenReturn(Mono.just(juriMember))

        StepVerifier.create(memberService.findMember(juriMember))
            .assertNext {
                assert(it.memberId == "juri")
                assert(it.memberPassword == "1")
            }
            .verifyComplete()
    }

    @Test
    fun findMemberFailureTest() {
        Mockito.`when`(memberRepo.findByMemberIdAndMemberPassword("juri", "1")).thenReturn(Mono.empty())

        StepVerifier.create(memberService.findMember(juriMember))
            .expectNextCount(0)
            .verifyComplete()
    }

    @Test
    fun duplicateIdCheckTrueTest() {
        Mockito.`when`(memberRepo.findByMemberId("juri")).thenReturn(Mono.just(juriMember))

        StepVerifier.create(memberService.duplicateIdCheck("juri"))
            .assertNext {
                assert(it == true)
            }
            .verifyComplete()
    }

    @Test
    fun duplicateIdCheckFalseTest() {
        Mockito.`when`(memberRepo.findByMemberId("juri")).thenReturn(Mono.empty())

        StepVerifier.create(memberService.duplicateIdCheck("juri"))
            .assertNext {
                assert(it == false)
            }
            .verifyComplete()
    }

}