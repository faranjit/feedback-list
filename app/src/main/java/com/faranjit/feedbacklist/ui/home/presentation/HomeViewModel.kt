package com.faranjit.feedbacklist.ui.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faranjit.feedbacklist.base.BaseViewModel
import com.faranjit.feedbacklist.ui.home.domain.Feedback
import com.faranjit.feedbacklist.ui.home.domain.interactor.GetFeedbacks

class HomeViewModel(private val getFeedbacks: GetFeedbacks) : BaseViewModel() {

    private val feedbacks = MutableLiveData<List<Feedback>>()
    val feedbackLiveData: LiveData<List<Feedback>>
        get() = feedbacks

    init {
        getFeedbacks()
    }

    fun getFeedbacks() =
        launchDataLoad(feedbacks) {
            getFeedbacks.execute()
        }

    fun getStarredFeedbacks() =
        launchDataLoad(feedbacks) {
            getFeedbacks.execute().filter { it.starred }
        }
}