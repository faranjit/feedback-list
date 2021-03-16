package com.faranjit.feedbacklist.ui.home.presentation

import android.os.Bundle
import android.view.View
import com.faranjit.feedbacklist.R
import com.faranjit.feedbacklist.base.BaseFragment
import com.faranjit.feedbacklist.base.viewBinding
import com.faranjit.feedbacklist.databinding.FragmentHomeBinding
import com.faranjit.feedbacklist.util.RecyclerScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding by viewBinding(FragmentHomeBinding::bind)

    private var feedbackAdapter: FeedbackListAdapter? = null
    private lateinit var scrollListener: RecyclerScrollListener

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initScrollListener()

        homeViewModel.feedbackLiveData.observe(viewLifecycleOwner, {
            if (feedbackAdapter == null) {
                feedbackAdapter = FeedbackListAdapter((it))
                binding.recyclerFeedbacks.adapter = feedbackAdapter
            } else {
                feedbackAdapter?.addItems(it)
            }

            scrollListener.setLoaded()
        })
    }

    override fun provideViewModel() = homeViewModel

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initScrollListener() {
        val layoutManager = binding.recyclerFeedbacks.layoutManager
        layoutManager?.let {
            scrollListener = RecyclerScrollListener(it).apply {
                setOnLoadMoreListener {
                    homeViewModel.fetchNexFeedbacks()
                }
            }

            binding.recyclerFeedbacks.addOnScrollListener(scrollListener)
        }
    }
}