package com.faranjit.feedbacklist.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.faranjit.feedbacklist.ui.home.domain.Feedback

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
abstract class BaseAdapter<DB : ViewDataBinding>(
    private val layoutResId: Int,
    private val clickListener: (Feedback) -> Unit
) : ListAdapter<Feedback, BaseViewHolder<DB>>(DiffUtilCallback()) {//RecyclerView.Adapter<BaseViewHolder<DB>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DB> {
        val binding: DB = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), layoutResId, parent, false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DB>, position: Int) {
        bindItem(holder.binding, getItem(position))
        holder.bind()

        holder.itemView.setOnClickListener {
            clickListener(getItem(position))
        }
    }

    abstract fun bindItem(binding: DB?, item: Feedback)
}