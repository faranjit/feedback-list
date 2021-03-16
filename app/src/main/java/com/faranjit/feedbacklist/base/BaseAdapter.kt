package com.faranjit.feedbacklist.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
abstract class BaseAdapter<T, DB : ViewDataBinding>(
    private val layoutResId: Int,
    private val items: List<T>
) : RecyclerView.Adapter<BaseViewHolder<DB>>() {

    protected var data: MutableList<T> = items.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DB> {
        val binding: DB = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), layoutResId, parent, false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DB>, position: Int) {
        bindItem(holder.binding, data[position])
        holder.bind()
    }

    override fun getItemCount(): Int = data.size

    abstract fun bindItem(binding: DB?, item: T)

    fun addItems(newItems: List<T>) {
        val count = itemCount
        data.addAll(newItems)

        notifyItemRangeInserted(count, newItems.size)
    }
}