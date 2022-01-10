package com.juri.TypingGameWorld.Member

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
@EnableReactiveMongoAuditing
@EnableReactiveMongoRepositories
class MemberApplication

fun main(args: Array<String>) {
	runApplication<MemberApplication>(*args)
}