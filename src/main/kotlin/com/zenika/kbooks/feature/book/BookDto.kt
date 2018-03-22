package com.zenika.kbooks.feature.book

import java.time.LocalDate

/**
 * Book dto that will be sent to clients.
 */
data class BookDto(var id: Long? = null,
                var title: String? = null,
                var publication: LocalDate? = null,
                var author: String? = null)