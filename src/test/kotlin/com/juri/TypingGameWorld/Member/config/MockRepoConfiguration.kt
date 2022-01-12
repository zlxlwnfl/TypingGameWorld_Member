package com.juri.TypingGameWorld.Member.config

import com.juri.TypingGameWorld.Member.business.repository.MemberRepository
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean

@TestConfiguration
class MockRepoConfiguration {

    @MockBean
    lateinit var memberRepo: MemberRepository

}