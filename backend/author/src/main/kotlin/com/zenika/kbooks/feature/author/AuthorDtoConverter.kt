package com.zenika.kbooks.feature.author

import com.zenika.kbooks.dto.AuthorDto
import com.zenika.kbooks.rest.IDtoConverter

/**
 * Convert author entity to dto.
 */
object AuthorDtoConverter : IDtoConverter<Author, AuthorDto> {
    override fun convert(entity: Author): AuthorDto {
        return AuthorDto(id = entity.id, name = entity.name)
    }
}