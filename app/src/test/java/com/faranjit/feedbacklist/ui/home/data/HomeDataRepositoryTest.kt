package com.faranjit.feedbacklist.ui.home.data

import com.faranjit.feedbacklist.BaseUnitTest
import com.faranjit.feedbacklist.DummyResponse
import com.faranjit.feedbacklist.ui.home.data.datasource.HomeRemoteDataSource
import com.faranjit.feedbacklist.ui.home.domain.FeedbackResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import org.junit.Assert
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times

/**
 * Created by Bulent Turkmen on 17.03.2021.
 */
@ExperimentalCoroutinesApi
class HomeDataRepositoryTest : BaseUnitTest() {

    @Mock
    private lateinit var remoteDataSource: HomeRemoteDataSource

    @InjectMocks
    private lateinit var repository: HomeDataRepository

    @Test
    fun `getFeedbacks should return all feedbacks`() {
        testScope.launch {
            // Given
            val dummyResponse =
                json.decodeFromString<FeedbackResponse>(DummyResponse.getFeedbacks()).feedbacks

            Mockito.`when`(remoteDataSource.getFeedbacks()).thenReturn(dummyResponse)

            // when
            val response = repository.getFeedbacks()

            // Then
            Assert.assertEquals(dummyResponse.size, response.size)
            Mockito.verify(remoteDataSource, times(1)).getFeedbacks()
        }
    }
}