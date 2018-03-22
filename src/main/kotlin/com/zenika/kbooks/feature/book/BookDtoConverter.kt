package com.zenika.kbooks.feature.book

/**
 * Convert book entity to book dto.
 */
class BookDtoConverter {
    fun convert(book: Book): BookDto {
        return BookDto(
                id = book.id,
                title = book.title,
                publication = book.publication,
                author = book.author

        )
    }
}