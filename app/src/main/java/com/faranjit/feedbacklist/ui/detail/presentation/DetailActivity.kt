package com.faranjit.feedbacklist.ui.detail.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.faranjit.feedbacklist.R
import com.faranjit.feedbacklist.base.BaseActivity
import com.faranjit.feedbacklist.base.viewBinding
import com.faranjit.feedbacklist.databinding.ActivityDetailsBinding
import com.faranjit.feedbacklist.ui.home.domain.Feedback
import com.faranjit.feedbacklist.ui.snippet.HtmlSnippetDialogFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.Behavior.DragCallback
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class DetailActivity : BaseActivity<DetailViewModel>() {

    companion object {
        const val KEY_FEEDBACK = "feedback"
    }

    private val binding by viewBinding(ActivityDetailsBinding::inflate)
    private val detailViewModel: DetailViewModel by viewModel()

    private var map: GoogleMap? = null

    override fun provideViewModel() = detailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = detailViewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(R.string.title_detail)

        val params = binding.appBar.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = AppBarLayout.Behavior()
        behavior.setDragCallback(object : DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return false
            }
        })
        params.behavior = behavior

        val feedback = intent.getParcelableExtra<Feedback>(KEY_FEEDBACK)
        // you can pass just id of the feedback and fetch details from api
        // or you can pass whole object and not send request to api
//        detailViewModel.feedback = feedback
        detailViewModel.getDetails(feedback?.id)

        binding.btnHtml.setOnClickListener {
            HtmlSnippetDialogFragment.newInstance(feedback?.html)
                .show(supportFragmentManager, HtmlSnippetDialogFragment::class.java.canonicalName)
        }

        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.feedback_map
        ) as? SupportMapFragment

        mapFragment?.getMapAsync {
            map = it
            feedback?.let {
                addMarker(feedback)
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

    private fun addMarker(feedback: Feedback) {
        map?.let {
            val coordinate = LatLng(feedback.location.lat, feedback.location.lon)
            val marker = it.addMarker(
                MarkerOptions()
                    .title(feedback.comment)
                    .position(coordinate)
            )

            it.moveCamera(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.Builder().target(coordinate).zoom(10f).build()
                )
            )
        }
    }
}