package com.faranjit.feedbacklist.ui.detail.domain

import com.faranjit.feedbacklist.ui.home.domain.Feedback

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
interface DetailRepository {

    /**
     * Gets details of the feedback by given id
     */
    suspend fun getDetail(id: String?): Feedback?
}