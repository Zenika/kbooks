package com.zenika.kbooks.config

import com.zenika.kbooks.feature.author.AuthorResource
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.stereotype.Component

@Component
class JerseyConfig() : ResourceConfig() {
    init {
        register(AuthorResource())
    }
}