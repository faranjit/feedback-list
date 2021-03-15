package com.faranjit.feedbacklist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faranjit.feedbacklist.base.BaseViewModel

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
class SharedViewModel: BaseViewModel() {

    private var counter = 0

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}