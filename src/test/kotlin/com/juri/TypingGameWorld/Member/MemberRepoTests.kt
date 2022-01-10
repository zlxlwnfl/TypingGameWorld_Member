package com.juri.TypingGameWorld.Member

import com.juri.TypingGameWorld.Member.business.domain.Member
import com.juri.TypingGameWorld.Member.business.repository.MemberRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.count
import org.springframework.data.mongodb.core.dropCollection
import org.springframework.test.context.event.annotation.AfterTestClass
import reactor.test.StepVerifier

@DataMongoTest
class MemberRepoTests {

    private val log = LoggerFactory.getLogger(MemberRepoTests::class.java)

    @Autowired
    lateinit var template: ReactiveMongoTemplate
    @Autowired
    lateinit var memberRepo: MemberRepository

    @BeforeEach
    fun init() {
        log.info("init")

        memberRepo.deleteAll().block()
        memberRepo.saveAll(
            mutableListOf(
                Member(memberId = "juri", memberPassword = "1"),
                Member(memberId = "sawual", memberPassword = "2"),
                Member(memberId = "hyuk", memberPassword = "3")
            )
        ).blockLast()
    }

    @AfterTestClass
    fun clean() = template.dropCollection<Member>().block()

    @Test
    fun testRepoCount() {
        template.count<Member>()
            .`as` { StepVerifier.create(it) }
            .assertNext {
                assert(it.equals(3.toLong()))
            }
            .verifyComplete()
    }

    @Test
    fun testFindByValidMemberId() {
        StepVerifier.create(memberRepo.findByMemberId("juri"))
            .assertNext {
                assert(it.memberId == "juri")
                assert(it.memberPassword == "1")
            }
            .verifyComplete()
    }

    @Test
    fun testFindByInvalidMemberId() {
        StepVerifier.create(memberRepo.findByMemberId("minsu"))
            .expectNextCount(0)
            .verifyComplete()
    }

    @Test
    fun testFindByValidMemberIdAndValidMemberPassword() {
        StepVerifier.create(memberRepo.findByMemberIdAndMemberPassword("juri", "1"))
            .assertNext {
                assert(it.memberId == "juri")
                assert(it.memberPassword == "1")
            }
            .verifyComplete()
    }

    @Test
    fun testFindByInvalidMemberIdAndMemberPassword() {
        StepVerifier.create(memberRepo.findByMemberIdAndMemberPassword("minsu", "1"))
            .expectNextCount(0)
            .verifyComplete()
    }

}