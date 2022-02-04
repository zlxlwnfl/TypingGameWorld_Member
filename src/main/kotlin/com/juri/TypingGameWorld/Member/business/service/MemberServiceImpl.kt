package com.juri.TypingGameWorld.Member.business.service

import com.juri.TypingGameWorld.Member.business.dto.MemberDTO
import com.juri.TypingGameWorld.Member.business.entity.Member
import com.juri.TypingGameWorld.Member.business.repository.MemberRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class MemberServiceImpl(private val memberRepo: MemberRepository) : MemberService {

    private val log = LoggerFactory.getLogger(MemberServiceImpl::class.java)

    override fun saveMember(memberRequest: MemberDTO.Request): Mono<MemberDTO.Response> =
        memberRequest.toMono()
            .flatMap {
                val member = Member(memberId = it.memberId)
                val savedMember = memberRepo.save(member)

                log.info("Member 저장 : ${it.memberId}")

                savedMember
            }.flatMap {
                MemberDTO.Response.fromMember(it).toMono()
            }

    override fun deleteMember(memberId: String): Mono<Void> =
        memberId.toMono()
            .flatMap {
                memberRepo.deleteById(memberId)
            }

    override fun findMemberByMemberId(memberId: String): Mono<MemberDTO.Response> =
        memberId.toMono()
            .flatMap {
                memberRepo.findByMemberId(it)
            }.flatMap {
                MemberDTO.Response.fromMember(it).toMono()
            }

    override fun findMembersByBestGame(bestGame: String): Mono<List<MemberDTO.Response>> =
        bestGame.toMono()
            .flatMapMany {
                memberRepo.findByBestGame(it)
            }.flatMap {
                MemberDTO.Response.fromMember(it).toMono()
            }.collectList()

    override fun updateBestGame(memberId: String, bestGame: String): Mono<MemberDTO.Response> =
        memberId.toMono()
            .flatMap {
                memberRepo.findByMemberId(it)
            }.flatMap {
                it.also {
                    it.bestGame = bestGame
                }

                memberRepo.save(it)
            }.flatMap {
                MemberDTO.Response.fromMember(it).toMono()
            }

    override fun deleteBestGame(memberId: String): Mono<Void> =
        memberId.toMono()
            .flatMap {
                memberRepo.deleteById(memberId)
            }

    override fun updateGameScores(memberId: String, gameName: String, gameScore: Int) {
        TODO("Not yet implemented")
    }

}