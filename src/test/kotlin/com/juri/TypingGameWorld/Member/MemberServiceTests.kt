package com.juri.TypingGameWorld.Member

import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
class MemberServiceTests {
/*
    @Autowired
    lateinit var memberService: MemberService

    @MockBean
    lateinit var memberRepo: MemberRepository
    private val juriMember: Member = Member(memberId = "juri", memberPassword = "1")

    @Test
    fun testSaveValidMember() {
        Mockito.`when`(memberRepo.save(juriMember))
            .thenReturn(Mono.just(juriMember))

        StepVerifier.create(memberService.saveMember(juriMember))
            .assertNext {
                assert(it.memberId == "juri")
                assert(it.memberPassword == "1")
            }
            .verifyComplete()
    }

    @Test
    fun testFindValidMember() {
        Mockito.`when`(memberRepo.findByMemberIdAndMemberPassword("juri", "1"))
            .thenReturn(Mono.just(juriMember))

        StepVerifier.create(memberService.findMember(juriMember))
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

        StepVerifier.create(memberService.findMember(juriMember))
            .expectNextCount(0)
            .verifyComplete()
    }

    @Test
    fun testValidDuplicateIdCheck() {
        Mockito.`when`(memberRepo.findByMemberId("juri"))
            .thenReturn(Mono.just(juriMember))

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