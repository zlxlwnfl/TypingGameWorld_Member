package com.juri.TypingGameWorld.Member.tests

import com.juri.TypingGameWorld.Member.business.dto.MemberDTO
import com.juri.TypingGameWorld.Member.business.entity.Member
import com.juri.TypingGameWorld.Member.business.repository.MemberRepository
import com.juri.TypingGameWorld.Member.business.service.MemberService
import com.juri.TypingGameWorld.Member.config.MockDefaultConfiguration
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(MockDefaultConfiguration::class)
class MemberServiceTests {

    @Autowired
    lateinit var memberService: MemberService
    @MockBean
    lateinit var memberRepo: MemberRepository

    private val MEMBER_ID: String = "test"

    private val testMember: Member = Member(
        memberId = MEMBER_ID
    )
    private val testValidMemberRequest : MemberDTO.Request = MemberDTO.Request(
        memberId = MEMBER_ID
    )

    @Test
    fun testSaveMemberSuccessWithValidMemberRequest() {
        Mockito.`when`(memberRepo.save(testMember))
            .thenReturn(Mono.just(testMember))

        StepVerifier.create(memberService.saveMember(testValidMemberRequest))
            .assertNext {
                assert(it.memberId == MEMBER_ID)
            }
            .verifyComplete()
    }
/*
    @Test
    fun testFindValidMember() {
        Mockito.`when`(memberRepo.findByMemberIdAndMemberPassword("juri", "1"))
            .thenReturn(Mono.just(testMember))

        StepVerifier.create(memberService.findMember(testMember))
            .assertNext {
                assert(it.memberId == "juri")
                assert(it.memberPassword == "1")
            }
            .verifyComplete()
    }

    @Test
    fun testFindInvalidMember() {
        Mockito.`when`(memberRepo.findByMemberIdAndMemberPassword("juri", "1"))
            .thenReturn(Mono.empty())

        StepVerifier.create(memberService.findMember(testMember))
            .expectNextCount(0)
            .verifyComplete()
    }

    @Test
    fun testValidDuplicateIdCheck() {
        Mockito.`when`(memberRepo.findByMemberId("juri"))
            .thenReturn(Mono.just(testMember))

        StepVerifier.create(memberService.duplicateIdCheck("juri"))
            .assertNext {
                assert(it == true)
            }
            .verifyComplete()
    }

    @Test
    fun testInvalidDuplicateIdCheck() {
        Mockito.`when`(memberRepo.findByMemberId("juri"))
            .thenReturn(Mono.empty())

        StepVerifier.create(memberService.duplicateIdCheck("juri"))
            .assertNext {
                assert(it == false)
            }
            .verifyComplete()
    }
*/
}