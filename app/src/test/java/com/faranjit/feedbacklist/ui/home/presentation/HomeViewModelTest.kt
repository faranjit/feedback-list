package com.faranjit.feedbacklist.ui.home.presentation

import com.faranjit.feedbacklist.BaseUnitTest
import com.faranjit.feedbacklist.getOrAwaitValue
import com.faranjit.feedbacklist.ui.home.domain.Feedback
import com.faranjit.feedbacklist.ui.home.domain.Images
import com.faranjit.feedbacklist.ui.home.domain.Location
import com.faranjit.feedbacklist.ui.home.domain.interactor.GetFeedbacks
import com.faranjit.feedbacklist.ui.home.domain.interactor.GetStarredFeedbacks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

/**
 * Created by Bulent Turkmen on 17.03.2021.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : BaseUnitTest() {

    @Mock
    lateinit var getFeedbacks: GetFeedbacks

    @Mock
    private lateinit var getStarredFeedbacks: GetStarredFeedbacks

    @InjectMocks
    private lateinit var viewModel: HomeViewModel

    @Test
    fun `getFeedbacks should trigger livedata`() = testScope.runBlockingTest {
        // Given

        val feedbacks = listOf(
            Feedback(
                id = "1",
                comment = "comment",
                rating = 5.0,
                location = Location(lat = 61.0, lon = 61.0),
                images = Images()
            )
        )

        // When
        `when`(getFeedbacks.execute()).thenReturn(feedbacks)
        viewModel.getFeedbacks()

        // Then
        assertEquals(
            feedbacks,
            viewModel.feedbackLiveData.getOrAwaitValue(),
            "contents are not same"
        )
    }

    @Test
    fun `getStarredFeedbacks should trigger livedata`() = testScope.runBlockingTest {
        // Given

        val feedbacks = listOf(
            Feedback(
                id = "1",
                comment = "comment",
                rating = 5.0,
                location = Location(lat = 61.0, lon = 61.0),
                images = Images(),
                starred = true
            )
        )

        // When
        `when`(getStarredFeedbacks.execute()).thenReturn(feedbacks)
        viewModel.getStarredFeedbacks()

        // Then
        assertEquals(
            feedbacks,
            viewModel.feedbackLiveData.getOrAwaitValue(),
            "contents are not same"
        )
    }
}