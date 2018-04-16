package com.zenika.kbooks.feature.author

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

/**
 * Rest endpoint for authors.
 */
@Path("/author")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
class AuthorResource {

    @Autowired
    private lateinit var authorService: AuthorService

    @GET
    @Path("/{id}")
    fun getAuthor(@PathParam("id") id: Long) = authorService.getAuthor(id)
}