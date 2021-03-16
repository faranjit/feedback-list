package com.faranjit.feedbacklist.ui.filter.domain.interactor

import com.faranjit.feedbacklist.base.BaseUseCase
import com.faranjit.feedbacklist.ui.filter.data.FilterData
import com.faranjit.feedbacklist.ui.filter.domain.FilterRepository

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class GetFilterData(
    private val filterRepository: FilterRepository
): BaseUseCase<FilterData, Unit>() {

    override suspend fun buildUseCaseObservable(params: Unit?) = filterRepository.getFilterData()
}