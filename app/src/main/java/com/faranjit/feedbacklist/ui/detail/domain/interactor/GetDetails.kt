package com.faranjit.feedbacklist.ui.detail.domain.interactor

import com.faranjit.feedbacklist.base.BaseUseCase
import com.faranjit.feedbacklist.ui.detail.domain.DetailRepository
import com.faranjit.feedbacklist.ui.home.domain.Feedback

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class GetDetails(
    private val repository: DetailRepository
): BaseUseCase<Feedback?, GetDetails.Params>() {

    override suspend fun buildUseCaseObservable(params: Params?) = repository.getDetail(params?.id)

    data class Params(val id: String?)
}