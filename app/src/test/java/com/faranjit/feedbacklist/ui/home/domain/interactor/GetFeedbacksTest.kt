package com.faranjit.feedbacklist.ui.home.domain.interactor

import com.faranjit.feedbacklist.BaseUnitTest
import com.faranjit.feedbacklist.ui.home.domain.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

/**
 * Created by Bulent Turkmen on 17.03.2021.
 */
@ExperimentalCoroutinesApi
class GetFeedbacksTest : BaseUnitTest() {

    @Mock
    private lateinit var repository: HomeRepository

    @InjectMocks
    private lateinit var getFeedbacks: GetFeedbacks

    @Test
    fun `getFeedbacks should be called`() {
        testScope.launch {
            // Given

            // when
            getFeedbacks.execute()

            // Then
            verify(repository, times(1)).getFeedbacks()
        }
    }
}