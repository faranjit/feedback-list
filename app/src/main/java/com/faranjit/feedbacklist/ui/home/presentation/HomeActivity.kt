package com.faranjit.feedbacklist.ui.home.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.faranjit.feedbacklist.R
import com.faranjit.feedbacklist.base.BaseActivity
import com.faranjit.feedbacklist.base.viewBinding
import com.faranjit.feedbacklist.databinding.ActivityMainBinding
import com.faranjit.feedbacklist.ui.detail.presentation.DetailActivity
import com.faranjit.feedbacklist.ui.filter.presentation.FilterActivity
import com.faranjit.feedbacklist.ui.home.domain.Feedback
import com.faranjit.feedbacklist.util.observeLiveData
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class HomeActivity : BaseActivity<HomeViewModel>() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val homeViewModel: HomeViewModel by viewModel()

    private var feedbackAdapter = FeedbackListAdapter(this::onFeedbackClick)

    override fun provideViewModel() = homeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
        setTitle(R.string.title_home)

        binding.recyclerFeedbacks.adapter = feedbackAdapter

        observeLiveData(homeViewModel.feedbackLiveData) {
            feedbackAdapter.submitList(it)
        }

        viewModel?.getFeedbacks()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_feedbacks, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_filter -> {
            startActivity(Intent(this, FilterActivity::class.java))
            true
        }

        R.id.action_starred -> {
            viewModel?.getStarredFeedbacks()
            true
        }

        R.id.action_all -> {
            viewModel?.getFeedbacks()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    private fun onFeedbackClick(feedback: Feedback) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtras(Bundle().apply {
                putParcelable(DetailActivity.KEY_FEEDBACK, feedback)
            })
        })
    }
}