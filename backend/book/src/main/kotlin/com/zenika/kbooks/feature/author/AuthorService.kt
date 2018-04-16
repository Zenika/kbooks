package com.zenika.kbooks.feature.author

import com.zenika.kbooks.dto.AuthorDto
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

/**
 * Author api client.
 */
@Service
class AuthorService {

    val url = "http://author:8080/author"

    /**
     * Return author with given id.
     */
    fun getAuthor(id: Long?) : AuthorDto? {
        return RestTemplate().getForObject("${url}/$id", AuthorDto::class.java)
    }
}