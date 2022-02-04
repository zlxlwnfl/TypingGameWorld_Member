package com.juri.TypingGameWorld.Member.business.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("members")
data class Member(

    @Id
    val id: String = "",
    val memberId: String,
    var bestGame: String = "",
    val gameScores: Map<String, Int> = mapOf()

)