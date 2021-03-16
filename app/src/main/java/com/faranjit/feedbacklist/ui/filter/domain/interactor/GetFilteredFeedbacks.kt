package com.faranjit.feedbacklist.ui.filter.domain.interactor

import com.faranjit.feedbacklist.base.BaseUseCase
import com.faranjit.feedbacklist.ui.filter.domain.FilterRepository
import com.faranjit.feedbacklist.ui.home.domain.Feedback

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
class GetFilteredFeedbacks(
    private val repository: FilterRepository
) : BaseUseCase<List<Feedback>, GetFilteredFeedbacks.Params>() {

    override suspend fun buildUseCaseObservable(params: Params?) =
        repository.getFeedbacks(params?.rating ?: 0, params?.label, params?.country, params?.city)

    data class Params(
        val rating: Int,
        val label: String? = null,
        val country: String? = null,
        val city: String? = null
    )
}