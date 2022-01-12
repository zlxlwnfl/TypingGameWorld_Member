package com.juri.TypingGameWorld.Member.config

import com.juri.TypingGameWorld.Member.global.config.MongoConfig
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean

@TestConfiguration
@EnableAutoConfiguration(exclude = [
    MongoReactiveAutoConfiguration::class,
    MongoReactiveDataAutoConfiguration::class,
])
class MockDefaultConfiguration {

    @MockBean
    lateinit var mongoConfig: MongoConfig

}