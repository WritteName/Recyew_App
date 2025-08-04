package com.example.appmov_prod.View.News

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmov_prod.Model.Content
import com.example.appmov_prod.R
import com.example.appmov_prod.ViewModel.ContentViewModel
import com.example.appmov_prod.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ContentViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.rvArticles.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvNews.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.featuredNews.observe(viewLifecycleOwner) {
            binding.carousel.adapter = CarouselAdapter(it) { item -> openWebView(item) }
        }

        viewModel.articles.observe(viewLifecycleOwner) {
            binding.rvArticles.adapter = ArticleAdapter(it) { item -> openWebView(item) }
        }

        viewModel.news.observe(viewLifecycleOwner) {
            binding.rvNews.adapter = NewsAdapter(it) { item -> openWebView(item) }
        }
    }

    private fun openWebView(item: Content) {
        val fragment = WebViewFragment.newInstance(item.contentUrl)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
