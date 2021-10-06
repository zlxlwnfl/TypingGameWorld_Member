package com.juri.TypingGameWorld.Member.repository

import com.juri.TypingGameWorld.Member.domain.Member
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface MemberRepository: ReactiveMongoRepository<Member, String>{

    fun findByMemberId(memberId: String): Mono<Member>
    fun findByMemberIdAndMemberPassword(memberId: String, memberPassword: String): Mono<Member>

}