package com.faranjit.feedbacklist.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class RecyclerScrollListener(private val mLayoutManager: RecyclerView.LayoutManager) :
    RecyclerView.OnScrollListener() {

    private var visibleThreshold = 5
    private var isLoading: Boolean = false
    private var lastVisibleItem: Int = 0

    private lateinit var mOnLoadMoreListener: OnLoadMoreListener

    fun setLoaded() {
        isLoading = false
    }

    fun getLoaded(): Boolean {
        return isLoading
    }

    fun setOnLoadMoreListener(block: () -> Unit) {
        this.mOnLoadMoreListener = object : OnLoadMoreListener {
            override fun onLoadMore() {
                block()
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            when (mLayoutManager) {
                is StaggeredGridLayoutManager -> {
                    val lastVisibleItemPositions = mLayoutManager.findLastVisibleItemPositions(null)
                    // get maximum element within the list
                    lastVisibleItem = getLastVisibleItem(lastVisibleItemPositions)
                }
                is GridLayoutManager -> {
                    lastVisibleItem = mLayoutManager.findLastVisibleItemPosition()
                }
                is LinearLayoutManager -> {
                    lastVisibleItem = mLayoutManager.findLastVisibleItemPosition()
                }
            }

            val totalItemCount = mLayoutManager.itemCount
            if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                mOnLoadMoreListener.onLoadMore()
                isLoading = true
            }
        }
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }
}

interface OnLoadMoreListener {
    fun onLoadMore()
}