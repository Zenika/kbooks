package com.zenika.kbooks.feature.book

import com.zenika.kbooks.feature.author.Author
import java.time.LocalDate
import javax.persistence.*

/**
 * JPA Book entity. All parameters have a default value because Hibernate need an empty constructor.
 */
@Entity
@Table(name = "book")
data class Book(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                var id: Long? = null,
                var title: String? = null,
                var publication: LocalDate? = null,
                @ManyToOne
                var author: Author? = null)