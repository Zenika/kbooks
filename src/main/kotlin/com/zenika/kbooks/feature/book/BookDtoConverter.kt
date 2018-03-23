package com.zenika.kbooks.feature.book

import com.zenika.kbooks.util.rest.IDtoConverter

/**
 * Convert book entity to book dto.
 */
object BookDtoConverter : IDtoConverter<Book, BookDto> {
    override fun convert(entity: Book): BookDto =
        BookDto(id = entity.id,
                title = entity.title,
                publication = entity.publication,
                authorId = entity.author?.id,
                authorName = entity.author?.name ?: "")
}