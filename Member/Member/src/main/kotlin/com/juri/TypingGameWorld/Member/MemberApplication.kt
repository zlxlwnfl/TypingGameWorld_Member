package com.juri.TypingGameWorld.Member

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class MemberApplication

fun main(args: Array<String>) {
	runApplication<MemberApplication>(*args)
}