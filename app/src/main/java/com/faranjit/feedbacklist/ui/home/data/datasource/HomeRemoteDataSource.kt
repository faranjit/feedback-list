package com.faranjit.feedbacklist.ui.home.data.datasource

import com.faranjit.feedbacklist.ui.home.data.HomeApi
import com.faranjit.feedbacklist.ui.home.domain.Feedback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
class HomeRemoteDataSource(private val homeApi: HomeApi) {

    /**
     * Get feedbacks from the source
     */
    suspend fun getFeedbacks(): List<Feedback> = withContext(Dispatchers.IO) {
        delay(1500)
        homeApi.getFeedbacks().feedbacks.sortedByDescending { it.created }
    }
}