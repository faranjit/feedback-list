package com.faranjit.feedbacklist.ui.home.domain

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
interface HomeRepository {

    /**
     * Get feedbacks from the source
     */
    suspend fun getFeedbacks(): FeedbackResponse
}