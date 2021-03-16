package com.faranjit.feedbacklist.ui.snippet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.faranjit.feedbacklist.R
import com.faranjit.feedbacklist.base.viewBinding
import com.faranjit.feedbacklist.databinding.LayoutHtmlSnippetDialogBinding

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
class HtmlSnippetDialogFragment : DialogFragment(R.layout.layout_html_snippet_dialog) {

    private var binding by viewBinding(LayoutHtmlSnippetDialogBinding::bind)

    companion object {
        const val KEY_HTML_SNIPPET = "html_snippet"

        fun newInstance(htmlSnippet: String?): HtmlSnippetDialogFragment {
            val args = Bundle()
            args.putString(KEY_HTML_SNIPPET, htmlSnippet)

            val fragment = HtmlSnippetDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.webView?.loadData(
            arguments?.getString(KEY_HTML_SNIPPET, "") ?: "",
            "text/html",
            "UTF-8"
        )
    }
}