package com.faranjit.feedbacklist.ui.home.data

import com.faranjit.feedbacklist.ui.home.data.datasource.HomeRemoteDataSource
import com.faranjit.feedbacklist.ui.home.domain.HomeRepository

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
class HomeDataRepository(private val remoteDataSource: HomeRemoteDataSource) : HomeRepository {

    override suspend fun getFeedbacks() = remoteDataSource.getFeedbacks()
}