package com.faranjit.feedbacklist.util

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.faranjit.feedbacklist.R
import com.google.android.material.slider.RangeSlider

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
inline fun <T> LifecycleOwner?.observeLiveData(
    liveData: LiveData<T>,
    crossinline observe: (T) -> Unit
) =
    this?.run {
        Observer<T> {
            observe(it)
        }.also {
            liveData.observe(this, it)
        }
    }

@BindingAdapter("app:imageUrl")
fun AppCompatImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this)
            .load(url)
            .error(R.drawable.ic_home_black_24dp)
            .into(this)
    }
}

@BindingAdapter("app:visibility")
fun View.setVisibility(content: Any?) {
    visibility = if (content == null) View.GONE else View.VISIBLE
}

@BindingAdapter(value = ["onValueChangeListener"])
fun RangeSlider.setOnValueChangeListener(listener: OnValueChangeListener) {
    addOnChangeListener { _: RangeSlider?, value: Float, _: Boolean ->
        listener.onValueChanged(value.toInt())
    }
}

interface OnValueChangeListener {
    fun onValueChanged(value: Int)
}