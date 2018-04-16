package com.zenika.kbooks.feature.book

import com.zenika.kbooks.dto.AuthorDto
import com.zenika.kbooks.dto.BookDto
import com.zenika.kbooks.rest.IDtoConverter

/**
 * Convert book entity to book dto.
 */
object BookDtoConverter : IDtoConverter<Pair<Book, AuthorDto>, BookDto> {
    override fun convert(entity: Pair<Book, AuthorDto>): BookDto =
            BookDto(id = entity.first.id,
                    title = entity.first.title,
                    publication = entity.first.publication,
                    authorId = entity.second.id,
                    authorName = entity.second.name ?: "")
}