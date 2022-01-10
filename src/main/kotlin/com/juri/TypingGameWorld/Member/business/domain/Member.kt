package com.juri.TypingGameWorld.Member.business.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("test")
data class Member(
    @Id
    val id: String = "",
    val memberId: String,
    val memberPassword: String
)