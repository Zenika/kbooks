package com.zenika.kbooks.dto

/**
 * Author dto that will be serialized and sent to clients.
 */
data class AuthorDto(var id: Long? = null,
                     var name: String? = null)