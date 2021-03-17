package com.faranjit.feedbacklist.ui.home.data.datasource

import com.faranjit.feedbacklist.network.FeedbackApi
import com.faranjit.feedbacklist.ui.home.domain.Feedback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
class HomeRemoteDataSource(private val feedbackApi: FeedbackApi) {

    /**
     * Get feedbacks from the source
     */
    suspend fun getFeedbacks(): List<Feedback> = withContext(Dispatchers.IO) {
        feedbackApi.getFeedbacks().feedbacks.sortedByDescending { it.rating }
    }

    /**
     * Get starred feedbacks from the source
     */
    suspend fun getStarredFeedbacks(): List<Feedback> = withContext(Dispatchers.IO) {
        feedbackApi.getFeedbacks().feedbacks
            .filter { it.starred }
            .sortedByDescending { it.rating }
    }
}