package com.faranjit.feedbacklist.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.faranjit.feedbacklist.R

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
@BindingAdapter("app:imageUrl")
fun AppCompatImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this)
            .load(url)
            .error(R.drawable.ic_home_black_24dp)
            .into(this)
    }
}