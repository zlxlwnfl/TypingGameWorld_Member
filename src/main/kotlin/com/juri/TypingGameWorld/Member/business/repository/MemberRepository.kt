package com.juri.TypingGameWorld.Member.business.repository

import com.juri.TypingGameWorld.Member.business.entity.Member
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface MemberRepository: ReactiveMongoRepository<Member, String>{

    fun findByMemberId(memberId: String): Mono<Member>
    fun findByBestGame(bestGame: String): Flux<Member>

}