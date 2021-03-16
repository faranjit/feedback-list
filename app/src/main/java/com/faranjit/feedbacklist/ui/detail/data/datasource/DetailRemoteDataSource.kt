package com.faranjit.feedbacklist.ui.detail.data.datasource

import com.faranjit.feedbacklist.network.FeedbackApi

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class DetailRemoteDataSource(private val feedbackApi: FeedbackApi) {

    suspend fun getDetail(id: String?) = feedbackApi.getDetails(id)
}