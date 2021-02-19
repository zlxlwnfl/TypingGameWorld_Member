package com.juri.TypingGameWorld.Member.service

import com.juri.TypingGameWorld.Member.domain.Test
import com.juri.TypingGameWorld.Member.handler.MemberHandler
import com.juri.TypingGameWorld.Member.repository.TestRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

@Service
class TestServiceImpl(private val testRepo: TestRepository) : TestService {

    private val log = LoggerFactory.getLogger(TestServiceImpl::class.java)

    override fun saveTest(test: Test): Mono<Test> =
        test?.toMono().flatMap { test ->
            log.info("저장 시도 : ${test.memberId} ${test.memberPassword}")
            testRepo.save(Test("", test.memberId, test.memberPassword))
        }

    override fun findTest(test: Test): Mono<Test> =
        test?.toMono().flatMap { test ->
            log.info("조회 시도 : ${test.memberId} ${test.memberPassword}")
            testRepo.findByMemberId(test.memberId).toMono()
        }

}