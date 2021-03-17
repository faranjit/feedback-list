package com.faranjit.feedbacklist.ui.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faranjit.feedbacklist.base.BaseViewModel
import com.faranjit.feedbacklist.ui.home.domain.Feedback
import com.faranjit.feedbacklist.ui.home.domain.interactor.GetFeedbacks
import com.faranjit.feedbacklist.ui.home.domain.interactor.GetStarredFeedbacks

class HomeViewModel(
    private val getFeedbacks: GetFeedbacks,
    private val getStarredFeedbacks: GetStarredFeedbacks
) : BaseViewModel() {

    private val feedbacks = MutableLiveData<List<Feedback>>()
    val feedbackLiveData: LiveData<List<Feedback>>
        get() = feedbacks

    init {
        getFeedbacks()
    }

    fun getFeedbacks() = launchDataLoad {
        feedbacks.value = getFeedbacks.execute()
    }

    fun getStarredFeedbacks() = launchDataLoad {
        feedbacks.value = getStarredFeedbacks.execute()
    }
}