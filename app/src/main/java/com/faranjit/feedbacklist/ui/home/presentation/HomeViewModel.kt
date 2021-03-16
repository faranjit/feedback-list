package com.faranjit.feedbacklist.ui.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faranjit.feedbacklist.base.BaseViewModel
import com.faranjit.feedbacklist.ui.home.domain.Feedback
import com.faranjit.feedbacklist.ui.home.domain.interactor.GetFeedbacks

class HomeViewModel(private val getFeedbacks: GetFeedbacks) : BaseViewModel() {

    companion object {
        private const val LIMIT = 20
    }

    private val feedbacks = MutableLiveData<List<Feedback>>()
    val feedbackLiveData: LiveData<List<Feedback>>
        get() = feedbacks

    private var currentPage = 0

    init {
        fetchNexFeedbacks()
    }

    fun fetchNexFeedbacks() =
        launchDataLoad(feedbacks) {
            getFeedbacks.execute().subList(currentPage * LIMIT, (++currentPage) * LIMIT)
        }
}