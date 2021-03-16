package com.faranjit.feedbacklist.ui.filter.data.datasource

import com.faranjit.feedbacklist.network.FeedbackApi
import com.faranjit.feedbacklist.ui.home.domain.Feedback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class FilterRemoteDataSource(private val feedbackApi: FeedbackApi) {

    suspend fun getFilterData() = withContext(Dispatchers.IO) {
        feedbackApi.getFilterData()
    }

    /**
     * Get feedbacks from the source
     */
    suspend fun getFeedbacks(): List<Feedback> = withContext(Dispatchers.IO) {
        feedbackApi.getFeedbacks().feedbacks.sortedByDescending { it.rating }
    }
}