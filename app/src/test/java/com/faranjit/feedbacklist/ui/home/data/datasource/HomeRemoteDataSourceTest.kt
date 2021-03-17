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
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*

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

    private lateinit var dummyResponse: FeedbackResponse

    @Before
    fun setup() {
        dummyResponse = json.decodeFromString(DummyResponse.getFeedbacks())
    }

    @Test
    fun `getFeedbacks should return all feedbacks`() {
        testScope.launch {
            // Given

            // when
            `when`(feedbackApi.getFeedbacks(null, null, null, null)).thenReturn(dummyResponse)
            val response = remoteDataSource.getFeedbacks()

            // Then
            Assert.assertEquals(dummyResponse.feedbacks.size, response.size)
            verify(feedbackApi, times(1)).getFeedbacks(null, null, null, null)
        }
    }

    @Test
    fun `getStarredFeedbacks should return just starred feedbacks`() {
        testScope.launch {
            // Given

            // when
            `when`(feedbackApi.getFeedbacks(null, null, null, null)).thenReturn(dummyResponse)
            val response = remoteDataSource.getStarredFeedbacks()

            // Then
            Assert.assertTrue(response.all { it.starred })
            verify(feedbackApi, times(1)).getFeedbacks(null, null, null, null)
        }
    }
}