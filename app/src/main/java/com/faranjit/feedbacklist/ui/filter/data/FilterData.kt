package com.faranjit.feedbacklist.ui.filter.data

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
data class FilterData(
    val labels: List<String>,
    val countries: List<Country>
)

data class Country(
    val name: String,
    val cities: List<String>
)