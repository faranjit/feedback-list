package com.faranjit.feedbacklist.ui.home.presentation

import com.faranjit.feedbacklist.R
import com.faranjit.feedbacklist.base.BaseAdapter
import com.faranjit.feedbacklist.databinding.ListItemFeedbackBinding
import com.faranjit.feedbacklist.ui.home.domain.Feedback

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class FeedbackListAdapter(
    val clickListener: (Feedback) -> Unit
) :
    BaseAdapter<ListItemFeedbackBinding>(R.layout.list_item_feedback, clickListener) {

    override fun bindItem(binding: ListItemFeedbackBinding?, item: Feedback) {
        binding?.feedback = item
    }
}