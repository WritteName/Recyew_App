package com.example.appmov_prod.View.News

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.appmov_prod.R

class WebViewFragment : Fragment() {

    companion object {
        private const val ARG_URL = "url"

        fun newInstance(url: String) = WebViewFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_URL, url)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_webview, container, false)
        val webView = view.findViewById<WebView>(R.id.webView)
        val url = arguments?.getString(ARG_URL)

        webView.settings.javaScriptEnabled = true
        url?.let { webView.loadUrl(it) }

        return view
    }
}
