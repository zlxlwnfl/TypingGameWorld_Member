package com.juri.TypingGameWorld.Member.service

import com.juri.TypingGameWorld.Member.domain.Member
import com.juri.TypingGameWorld.Member.repository.MemberRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import reactor.kotlin.core.publisher.toMono

@Service
class MemberServiceImpl(private val memberRepo: MemberRepository) : MemberService {

    private val log = LoggerFactory.getLogger(MemberServiceImpl::class.java)

    override fun saveMember(member: Member): Mono<Member> =
        member?.toMono().flatMap {
            log.info("저장 시도 : ${it.memberId} ${it.memberPassword}")
            memberRepo.save(it)
        }

    override fun findMember(member: Member): Mono<Member> =
        member?.toMono().flatMap {
            log.info("조회 시도 : ${it.memberId} ${it.memberPassword}")
            memberRepo.findByMemberIdAndMemberPassword(it.memberId, it.memberPassword)
                .switchIfEmpty {
                    Mono.empty()
                }
        }

    override fun duplicateIdCheck(memberId: String): Mono<Boolean> =
        memberId?.toMono().flatMap { memberId ->
            log.info("아이디 중복 조회 시도 : $memberId")
            memberRepo.findByMemberId(memberId)
                .flatMap {
                    Mono.just(true)
                }.switchIfEmpty {
                    Mono.just(false)
                }
        }

}