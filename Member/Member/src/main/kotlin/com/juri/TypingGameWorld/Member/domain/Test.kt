package com.juri.TypingGameWorld.Member.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("test")
class Test(
    @Id
    val id: String,
    var name: String,
    var message: String
)