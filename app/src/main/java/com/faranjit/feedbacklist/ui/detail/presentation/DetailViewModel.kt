package com.faranjit.feedbacklist.ui.detail.presentation

import androidx.databinding.ObservableField
import com.faranjit.feedbacklist.base.BaseViewModel
import com.faranjit.feedbacklist.ui.detail.domain.interactor.GetDetails
import com.faranjit.feedbacklist.ui.home.domain.Feedback

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class DetailViewModel(
    private val getDetails: GetDetails
) : BaseViewModel() {

//    var feedback: Feedback? = null
//        set(value) {
//            field = value
//            feedbackObservable.set(value)
//            labelsObservable.set(value?.labels?.joinToString())
//        }

    val feedbackObservable = ObservableField<Feedback>()
    val labelsObservable = ObservableField<String>()

    fun getDetails(id: String?) = launchDataLoad {
        val details = getDetails.execute(GetDetails.Params(id))
        feedbackObservable.set(details)
        labelsObservable.set(details?.labels?.joinToString())
    }
}