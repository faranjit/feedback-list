package com.faranjit.feedbacklist.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.faranjit.feedbacklist.R

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected var viewModel: VM? = null

    private val progressDialog: AppCompatDialog? by lazy {
        AppCompatDialog(this, R.style.ProgressDialog).apply {
            setContentView(R.layout.dialog_loading)
            setCancelable(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = provideViewModel()
        viewModel?.loadingLiveData?.observe(this, {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    abstract fun provideViewModel(): VM

    fun showLoading() {
        progressDialog?.show()
    }

    fun hideLoading() {
        progressDialog?.dismiss()
    }
}