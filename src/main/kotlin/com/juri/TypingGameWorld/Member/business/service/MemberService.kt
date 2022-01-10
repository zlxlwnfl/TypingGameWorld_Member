package com.juri.TypingGameWorld.Member.business.service

import com.juri.TypingGameWorld.Member.business.dto.MemberDTO
import com.juri.TypingGameWorld.Member.business.entity.Member
import reactor.core.publisher.Mono

interface MemberService {

    fun saveMember(memberRequest: MemberDTO.Request): Mono<Member>
    fun deleteMember(memberId: String)
    fun findMemberByMemberId(memberId: String): Mono<Member>
    fun findMembersByBestGame(bestGame: String): Mono<List<Member>>
    fun updateBestGame(memberId: String, bestGame: String)
    fun deleteBestGame(memberId: String)
    fun updateGameScores(memberId: String, gameName: String, gameScore: Int) // [TODO] JMS message handler로 나중에 구현

}