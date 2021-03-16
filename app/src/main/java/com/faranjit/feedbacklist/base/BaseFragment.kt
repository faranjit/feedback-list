package com.faranjit.feedbacklist.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.faranjit.feedbacklist.R

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(
    layoutId: Int
) : Fragment(layoutId) {

    private var viewModel: VM? = null

    private val progressDialog: AppCompatDialog? by lazy {
        AppCompatDialog(context, R.style.ProgressDialog).apply {
            setContentView(R.layout.dialog_loading)
            setCancelable(false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = provideViewModel()
    }

    abstract fun provideViewModel(): VM

    private fun showLoading() {
        (activity as? BaseActivity<*>)?.showLoading()
    }

    private fun hideLoading() {
        (activity as? BaseActivity<*>)?.hideLoading()
    }
}