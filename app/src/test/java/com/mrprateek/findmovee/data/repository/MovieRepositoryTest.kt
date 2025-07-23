package com.mrprateek.findmovee.data.repository

import com.mrprateek.findmovee.data.api.MovieApiService
import com.mrprateek.findmovee.domain.model.Movie
import com.mrprateek.findmovee.domain.model.MovieResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class MovieRepositoryTest {
    private lateinit var repository: MovieRepository
    private val apiService: MovieApiService = mock()

    @Before
    fun setup() {
        repository = MovieRepository(apiService)
    }

    @Test
    fun `fetchPopularMovies returns expected list`() = runBlocking {
        // Arrange
        val mockMovies = listOf(
            Movie(1, "Inception", "/poster1.jpg", "Overview", "2010-07-16"),
            Movie(2, "Interstellar", "/poster2.jpg", "Overview", "2014-11-07")
        )
        val mockResponse = MovieResponse(1, mockMovies)

        whenever(apiService.getPopularMovies()).thenReturn(mockResponse)

        // Act
        val result = repository.fetchPopularMovies()

        // Assert
        assertEquals(2, result.results.size)
        assertEquals("Inception", result.results.first().title)
    }
}