package com.zenika.kbooks.dto

import java.time.LocalDate

/**
 * Book dto that will serialized and sent to clients.
 */
data class BookDto(var id: Long? = null,
                var title: String? = null,
                var publication: LocalDate? = null,
                var authorId: Long? = null,
                var authorName: String? = null)