package com.faranjit.feedbacklist.ui.filter.domain

import com.faranjit.feedbacklist.ui.filter.data.FilterData
import com.faranjit.feedbacklist.ui.home.domain.Feedback

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
interface FilterRepository {

    /**
     * Retrieves data to be used for filtering
     */
    suspend fun getFilterData(): FilterData

    /**
     * Get feedbacks from the source
     */
    suspend fun getFeedbacks(
        rating: Int,
        label: String? = null,
        country: String? = null,
        city: String? = null
    ): List<Feedback>
}