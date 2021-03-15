package com.faranjit.feedbacklist.ui.home.domain.interactor

import com.faranjit.feedbacklist.base.BaseUseCase
import com.faranjit.feedbacklist.ui.home.domain.FeedbackResponse
import com.faranjit.feedbacklist.ui.home.domain.HomeRepository

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
class GetFeedbacks(private val repository: HomeRepository) : BaseUseCase<FeedbackResponse, Unit>() {

    override suspend fun buildUseCaseObservable(params: Unit?) = repository.getFeedbacks()
}