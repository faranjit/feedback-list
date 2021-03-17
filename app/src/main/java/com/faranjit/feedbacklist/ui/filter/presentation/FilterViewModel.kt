package com.faranjit.feedbacklist.ui.filter.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faranjit.feedbacklist.base.BaseViewModel
import com.faranjit.feedbacklist.ui.filter.data.Country
import com.faranjit.feedbacklist.ui.filter.data.FilterData
import com.faranjit.feedbacklist.ui.filter.domain.interactor.GetFilterData
import com.faranjit.feedbacklist.ui.filter.domain.interactor.GetFilteredFeedbacks
import com.faranjit.feedbacklist.ui.home.domain.Feedback

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class FilterViewModel(
    private val getFilterData: GetFilterData,
    private val filter: GetFilteredFeedbacks
) : BaseViewModel() {

    private val feedbacks = MutableLiveData<List<Feedback>>()
    val feedbackLiveData: LiveData<List<Feedback>>
        get() = feedbacks

    private var labels = MutableLiveData<List<String>>()
    val labelsLiveData: LiveData<List<String>>
        get() = labels

    private var countries = MutableLiveData<List<Country>>()
    val countriesLiveData: LiveData<List<Country>>
        get() = countries

    private var cities = MutableLiveData<List<String>>()
    val citiesLiveData: LiveData<List<String>>
        get() = cities

    private var filterData: FilterData? = null
        set(value) {
            field = value

            if (value != null) {
                labels.value = value.labels
                countries.value = value.countries

                if (value.countries.isNotEmpty()) {
                    cities.value = value.countries[0].cities
                }
            }
        }

    private var selectedRating = 0

    var selectedLabel: String? = null
    var selectedCity: String? = null
    var selectedCountry: String? = null
        set(value) {
            field = value
            cities.value = countries.value?.find { it.name == value }?.cities
        }

    init {
        getFilterData()
    }

    private fun getFilterData() = launchDataLoad {
        filterData = getFilterData.execute()
    }

    /**
     * Rating slider change listener
     */
    fun onValueChanged(value: Int) {
        selectedRating = value
    }

    fun filter() = launchDataLoad {
        feedbacks.value = filter.execute(
            GetFilteredFeedbacks.Params(
                selectedRating, selectedLabel, selectedCountry, selectedCity
            )
        )
    }
}