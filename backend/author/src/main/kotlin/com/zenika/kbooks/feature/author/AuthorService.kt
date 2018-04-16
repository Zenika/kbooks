package com.zenika.kbooks.feature.author

import com.zenika.kbooks.dto.AuthorDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.ws.rs.NotFoundException

/**
 * Author service class.
 */
@Service
class AuthorService {
    @Autowired
    private lateinit var authorRepository: IAuthorRepository

    @Transactional(readOnly = true)
    fun getAuthor(id: Long): AuthorDto {
        // Find author in repository and throw exception if it was not found.
        return authorRepository.findById(id)
                .map(AuthorDtoConverter::convert)
                .orElseThrow { throw NotFoundException("Book $id does not exist") }
    }
}