package com.faranjit.feedbacklist.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
open class BaseViewHolder<DB: ViewDataBinding>(val binding: DB) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.executePendingBindings()
    }
}