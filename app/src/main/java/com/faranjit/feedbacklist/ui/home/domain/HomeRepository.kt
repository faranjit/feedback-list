package com.faranjit.feedbacklist.ui.home.domain

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
interface HomeRepository {

    /**
     * Get feedbacks from the source
     */
    suspend fun getFeedbacks(): List<Feedback>

    /**
     * Get starred feedbacks from the source
     */
    suspend fun getStarredFeedbacks(): List<Feedback>
}