package com.faranjit.feedbacklist.ui.home.data.datasource

import com.faranjit.feedbacklist.BaseUnitTest
import com.faranjit.feedbacklist.DummyResponse
import com.faranjit.feedbacklist.network.FeedbackApi
import com.faranjit.feedbacklist.ui.home.domain.FeedbackResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import org.junit.Assert
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times

/**
 * Created by Bulent Turkmen on 17.03.2021.
 */
@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
class HomeRemoteDataSourceTest : BaseUnitTest() {

    @Mock
    lateinit var feedbackApi: FeedbackApi

    @InjectMocks
    private lateinit var remoteDataSource: HomeRemoteDataSource

    @Test
    fun `getFeedbacks should return all feedbacks`() {
        testScope.launch {
            // Given
            val dummyResponse =
                json.decodeFromString<FeedbackResponse>(DummyResponse.getFeedbacks())
            `when`(feedbackApi.getFeedbacks(null, null, null, null)).thenReturn(dummyResponse)

            // when
            val response = remoteDataSource.getFeedbacks()

            // Then
            Assert.assertEquals(dummyResponse.feedbacks.size, response.size)
            Mockito.verify(feedbackApi, times(1)).getFeedbacks(null, null, null, null)
        }
    }
}