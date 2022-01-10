package com.juri.TypingGameWorld.Member.business.dto

class MemberDTO {

    data class Request(
        val memberId: String
    )
    data class Response(
        val memberId: String,
        val bestGame: String,
        val gameScores: Map<String, Int>
    )

}