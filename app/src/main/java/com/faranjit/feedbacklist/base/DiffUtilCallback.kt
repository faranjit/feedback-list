package com.faranjit.feedbacklist.base

import androidx.recyclerview.widget.DiffUtil
import com.faranjit.feedbacklist.ui.home.domain.Feedback

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class DiffUtilCallback : DiffUtil.ItemCallback<Feedback>() {

    override fun areItemsTheSame(oldItem: Feedback, newItem: Feedback) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Feedback, newItem: Feedback) = oldItem.id == newItem.id
}