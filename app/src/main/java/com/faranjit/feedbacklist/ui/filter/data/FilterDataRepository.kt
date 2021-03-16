package com.faranjit.feedbacklist.ui.filter.data

import com.faranjit.feedbacklist.ui.filter.data.datasource.FilterRemoteDataSource
import com.faranjit.feedbacklist.ui.filter.domain.FilterRepository
import com.faranjit.feedbacklist.ui.home.domain.Feedback

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class FilterDataRepository(
    private val remoteDataSource: FilterRemoteDataSource
) : FilterRepository {

    override suspend fun getFilterData(): FilterData {
        val feedbacks = remoteDataSource.getFilterData().feedbacks

        val labels = mutableListOf<String>()
        val countriesMap = mutableMapOf<String, MutableSet<String>>()

        feedbacks.forEach { feedback ->
            if (!feedback.labels.isNullOrEmpty()) {
                labels.addAll(feedback.labels)
            }

            feedback.location.country?.let {
                if (!countriesMap.containsKey(it)) {
                    if (feedback.location.city != null) {
                        countriesMap[it] = mutableSetOf(feedback.location.city)
                    }
                } else {
                    if (feedback.location.city != null) {
                        countriesMap[it]!!.add(feedback.location.city)
                    }
                }
            }
        }

        val countries = mutableListOf<Country>()
        for (entry in countriesMap) {
            countries.add(Country(entry.key, entry.value.toList()))
        }

        return FilterData(labels.distinct(), countries)
    }

    override suspend fun getFeedbacks(
        rating: Int,
        label: String?,
        country: String?,
        city: String?
    ): List<Feedback> {
        // Normally we need to send a request to the server, but we don't have such a possibility.
        // so we will filter dummy response manually
        val feedbacks = remoteDataSource.getFeedbacks()

        return feedbacks.filter {
            var pass = it.rating >= rating
            pass = pass and (label != null && it.labels?.contains(label) == true)
            pass = pass and (country != null && it.location.country == country)
            pass = pass and (city != null && it.location.city == city)

            pass
        }
    }
}