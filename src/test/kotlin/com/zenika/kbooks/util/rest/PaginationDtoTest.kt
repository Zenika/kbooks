package com.zenika.kbooks.util.rest

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class PaginationDtoTest {

    @Test
    fun testToPaginateWithPageZero() {
        val pagination = PaginationDto(page = 0, size = 2, sort = null)
        val pageable = pagination.toPageable()

        Assert.assertEquals(1, pageable.pageNumber)
    }

    @Test
    fun testToPaginateWithSizeZero() {
        val pagination = PaginationDto(page = 1, size = 0, sort = null)
        val pageable = pagination.toPageable()

        Assert.assertEquals(10, pageable.pageSize)
    }

    @Test
    fun testToPaginateWithMaxSize() {
        val pagination = PaginationDto(page = 1, size = 30, sort = null)
        val pageable = pagination.toPageable(20)

        Assert.assertEquals(20, pageable.pageSize)
    }
}