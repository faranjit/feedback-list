package com.faranjit.feedbacklist.ui.notifications

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.faranjit.feedbacklist.R
import com.faranjit.feedbacklist.base.viewBinding
import com.faranjit.feedbacklist.databinding.FragmentNotificationsBinding
import com.faranjit.feedbacklist.ui.SharedViewModel

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private val notificationsViewModel: SharedViewModel by activityViewModels()
    private var _binding by viewBinding(FragmentNotificationsBinding::bind)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}