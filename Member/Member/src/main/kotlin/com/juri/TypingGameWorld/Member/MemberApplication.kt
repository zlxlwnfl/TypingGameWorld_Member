package com.juri.TypingGameWorld.Member

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.web.bind.annotation.CrossOrigin

@EnableReactiveMongoAuditing
@EnableReactiveMongoRepositories
@SpringBootApplication
open class MemberApplication

fun main(args: Array<String>) {
	runApplication<MemberApplication>(*args)
}