package com.juri.TypingGameWorld.Member.tests

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class MemberRepoTests {
/*
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
*/
}