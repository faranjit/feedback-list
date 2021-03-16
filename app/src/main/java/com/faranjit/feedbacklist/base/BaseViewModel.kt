package com.faranjit.feedbacklist.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Bulent Turkmen on 15.03.2021.
 */
abstract class BaseViewModel : ViewModel() {

    private val loading = MutableLiveData<Boolean>(false)

    /**
     * Show a loading spinner if true
     */
    val loadingLiveData: LiveData<Boolean>
        get() = loading

    fun <T> launchDataLoad(resultLiveData: MutableLiveData<T>, block: suspend () -> T): Job {
        loading.value = true
        return viewModelScope.launch {
            try {
                resultLiveData.value = block()
            } catch (error: Throwable) {
                Log.e(javaClass.canonicalName, error.message, error)
            } finally {
                loading.value = false
            }
        }
    }
}