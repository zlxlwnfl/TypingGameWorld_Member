package com.juri.TypingGameWorld.Member.business.dto

import com.juri.TypingGameWorld.Member.business.entity.Member

class MemberDTO {

    data class Request(
        val memberId: String
    )
    class Response(
        val memberId: String,
        val bestGame: String,
        val gameScores: Map<String, Int>
    ) {
        companion object {
            fun fromMember(member: Member) = MemberDTO.Response(
                memberId = member.memberId,
                bestGame = member.bestGame,
                gameScores = member.gameScores
            )
        }
    }

}