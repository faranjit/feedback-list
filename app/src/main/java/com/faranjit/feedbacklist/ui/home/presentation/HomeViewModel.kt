package com.faranjit.feedbacklist.ui.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faranjit.feedbacklist.base.BaseViewModel
import com.faranjit.feedbacklist.ui.home.domain.interactor.GetFeedbacks

class HomeViewModel(private val getFeedbacks: GetFeedbacks) : BaseViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    init {
        getFeedbacks()
    }

    private fun getFeedbacks() =
        launchDataLoad(_text) {
            getFeedbacks.execute().feedbacks.joinToString(separator = " ") { it.id }
        }
}