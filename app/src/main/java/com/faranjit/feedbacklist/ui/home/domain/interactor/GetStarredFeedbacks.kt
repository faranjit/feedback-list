package com.faranjit.feedbacklist.ui.home.domain.interactor

import com.faranjit.feedbacklist.base.BaseUseCase
import com.faranjit.feedbacklist.ui.home.domain.Feedback
import com.faranjit.feedbacklist.ui.home.domain.HomeRepository

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
class GetStarredFeedbacks(private val repository: HomeRepository) : BaseUseCase<List<Feedback>, Unit>() {

    override suspend fun buildUseCaseObservable(params: Unit?) = repository.getStarredFeedbacks()
}