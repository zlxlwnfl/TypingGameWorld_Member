package com.juri.TypingGameWorld.Member.business.service

import com.juri.TypingGameWorld.Member.business.dto.MemberDTO
import reactor.core.publisher.Mono

interface MemberService {

    fun saveMember(memberRequest: MemberDTO.Request): Mono<MemberDTO.Response>
    fun deleteMember(memberId: String) : Mono<Void>
    fun findMemberByMemberId(memberId: String): Mono<MemberDTO.Response>
    fun findMembersByBestGame(bestGame: String): Mono<List<MemberDTO.Response>>
    fun updateBestGame(memberId: String, bestGame: String): Mono<MemberDTO.Response>
    fun deleteBestGame(memberId: String): Mono<Void>
    fun updateGameScores(memberId: String, gameName: String, gameScore: Int) // [TODO] JMS message handler로 나중에 구현

}