package com.zenika.kbooks.config

import com.zenika.kbooks.feature.book.BookResource
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component

@Component
class JerseyConfig: ResourceConfig {
    constructor(): super() {
        register(BookResource())
    }
}