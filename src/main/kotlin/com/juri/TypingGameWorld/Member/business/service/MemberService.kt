package com.juri.TypingGameWorld.Member.business.service

import com.juri.TypingGameWorld.Member.business.domain.Member
import reactor.core.publisher.Mono

interface MemberService {

    fun saveMember(member: Member): Mono<Member>
    fun findMember(member: Member): Mono<Member>
    fun duplicateIdCheck(memberId: String): Mono<Boolean>

}