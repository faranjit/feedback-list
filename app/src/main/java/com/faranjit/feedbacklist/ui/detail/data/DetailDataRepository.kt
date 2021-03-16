package com.faranjit.feedbacklist.ui.detail.data

import com.faranjit.feedbacklist.ui.detail.data.datasource.DetailRemoteDataSource
import com.faranjit.feedbacklist.ui.detail.domain.DetailRepository

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class DetailDataRepository(
    private val remoteDataSource: DetailRemoteDataSource
) : DetailRepository {

    override suspend fun getDetail(id: String?) =
        remoteDataSource.getDetail(id).feedbacks.find { it.id == id }
}