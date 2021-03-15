package com.faranjit.feedbacklist.ui.home.data

import com.faranjit.feedbacklist.ui.home.domain.FeedbackResponse
import retrofit2.http.GET

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
interface HomeApi {

    /**
     * Get feedbacks from the source
     */
    @GET("/endpoint")
    suspend fun getFeedbacks(): FeedbackResponse
}