package com.mrprateek.findmovee.presentation.viewmodel

import app.cash.turbine.test
import com.mrprateek.findmovee.data.repository.MovieRepository
import com.mrprateek.findmovee.domain.model.Movie
import com.mrprateek.findmovee.domain.model.MovieResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: MovieRepository
    private lateinit var viewModel: MovieListViewModel

    @Before
    fun setup() {
        repository = mock()
        Dispatchers.setMain(testDispatcher)
        viewModel = MovieListViewModel(repository)
    }

    @Test
    fun `viewModel loads movies correctly`() = runTest {
        // Arrange
        val mockMovies = listOf(
            Movie(1, "Inception", "/poster1.jpg", "Overview", "2010-07-16")
        )
        val mockResponse = MovieResponse(1, mockMovies)
        whenever(repository.fetchPopularMovies()).thenReturn(mockResponse)

        // Act
        viewModel.loadPopularMovies()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        viewModel.movies.test {
            val movies = awaitItem()
            assertEquals("Inception", movies.first().title)
        }
    }
}