package com.faranjit.feedbacklist.ui.filter.presentation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.faranjit.feedbacklist.R
import com.faranjit.feedbacklist.base.BaseActivity
import com.faranjit.feedbacklist.base.viewBinding
import com.faranjit.feedbacklist.databinding.ActivityFilterBinding
import com.faranjit.feedbacklist.ui.detail.presentation.DetailActivity
import com.faranjit.feedbacklist.ui.home.domain.Feedback
import com.faranjit.feedbacklist.ui.home.presentation.FeedbackListAdapter
import com.faranjit.feedbacklist.util.observeLiveData
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterActivity : BaseActivity<FilterViewModel>() {

    private val binding by viewBinding(ActivityFilterBinding::inflate)
    private val filterViewModel: FilterViewModel by viewModel()

    private var feedbackAdapter = FeedbackListAdapter(this::onFeedbackClick)

    private var labelsAdapter: ArrayAdapter<String>? = null
    private var countriesAdapter: ArrayAdapter<String>? = null
    private var citiesAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel

        setTitle(R.string.title_filter)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setViews()
        filterViewModel.run {
            observeLiveData(labelsLiveData) {
                labelsAdapter?.addAll(it)
                labelsAdapter?.notifyDataSetChanged()
            }

            observeLiveData(countriesLiveData) {
                countriesAdapter?.addAll(it.map { c -> c.name })
                countriesAdapter?.notifyDataSetChanged()
            }

            observeLiveData(citiesLiveData) {
                citiesAdapter?.clear()
                citiesAdapter?.addAll(it)
                citiesAdapter?.notifyDataSetChanged()
            }

            observeLiveData(feedbackLiveData) {
                feedbackAdapter.submitList(it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun provideViewModel() = filterViewModel

    private fun createSpinnerSelectedListener(block: (position: Int) -> Unit) =
        object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                block(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    private fun setViews() {
        labelsAdapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                mutableListOf()
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

        countriesAdapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                mutableListOf()
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

        citiesAdapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                mutableListOf()
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

        binding.run {
            btnFilter.setOnClickListener { filterViewModel.filter() }
            recyclerFeedbacks.adapter = feedbackAdapter

            countrySpinner.adapter = countriesAdapter
            countrySpinner.onItemSelectedListener = createSpinnerSelectedListener {
                filterViewModel.selectedCountry = countriesAdapter?.getItem(it)
            }

            citySpinner.adapter = citiesAdapter
            citySpinner.onItemSelectedListener = createSpinnerSelectedListener {
                filterViewModel.selectedCity = citiesAdapter?.getItem(it)
            }

            labelSpinner.adapter = labelsAdapter
            labelSpinner.onItemSelectedListener = createSpinnerSelectedListener {
                filterViewModel.selectedLabel = labelsAdapter?.getItem(it)
            }
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