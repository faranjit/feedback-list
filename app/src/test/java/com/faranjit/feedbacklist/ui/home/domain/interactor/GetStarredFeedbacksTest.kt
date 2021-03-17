package com.faranjit.feedbacklist.ui.home.domain.interactor

import com.faranjit.feedbacklist.BaseUnitTest
import com.faranjit.feedbacklist.ui.home.domain.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by Bulent Turkmen on 17.03.2021.
 */
@ExperimentalCoroutinesApi
class GetStarredFeedbacksTest : BaseUnitTest() {

    @Mock
    private lateinit var repository: HomeRepository

    @InjectMocks
    private lateinit var getStarredFeedbacks: GetStarredFeedbacks

    @Test
    fun `getStarredFeedbacks should be called`() {
        testScope.launch {
            // Given

            // when
            getStarredFeedbacks.execute()

            // Then
            Mockito.verify(repository, Mockito.times(1)).getStarredFeedbacks()
        }
    }
}