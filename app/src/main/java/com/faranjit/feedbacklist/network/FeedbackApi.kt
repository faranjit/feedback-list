package com.faranjit.feedbacklist.network

import com.faranjit.feedbacklist.ui.home.domain.FeedbackResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
interface FeedbackApi {

    /**
     * Get feedbacks from the source
     */
    @GET("/feedback")
    suspend fun getFeedbacks(
        @Query("rating") rating: Int? = null,
        @Query("label") label: String? = null,
        @Query("country") country: String? = null,
        @Query("city") city: String? = null
    ): FeedbackResponse

    @GET("/")
    suspend fun getFilterData(): FeedbackResponse

    @GET("/detail/{id}")
    suspend fun getDetails(@Path("id") id: String?): FeedbackResponse
}