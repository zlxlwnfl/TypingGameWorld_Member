package com.juri.TypingGameWorld.Member.repository

import com.juri.TypingGameWorld.Member.domain.Test
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface TestRepository: ReactiveMongoRepository<Test, String>{

    fun findByMemberId(memberId: String): Flux<Test>

}