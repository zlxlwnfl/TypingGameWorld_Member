package com.juri.TypingGameWorld.Member.service

import com.juri.TypingGameWorld.Member.domain.Test
import reactor.core.publisher.Mono

interface TestService {

    fun saveTest(test: Test): Mono<Test>
    fun findTest(test: Test): Mono<Test>

}