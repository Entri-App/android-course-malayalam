package me.kariot.mynewsapplication.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import me.kariot.mynewsapplication.databinding.FragmentNewsBinding
import me.kariot.mynewsapplication.databinding.LayoutErrorBinding
import me.kariot.mynewsapplication.model.newsModel.NewsResponse
import me.kariot.mynewsapplication.presenter.NewsPresenter
import me.kariot.mynewsapplication.presenter.NewsView
import me.kariot.mynewsapplication.ui.adapter.NewsAdapter

class NewsFragment : Fragment(), NewsView {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private var _errorBinding: LayoutErrorBinding? = null
    private val errorBinding get() = _errorBinding!!


    private var isLoading = false
    private var isFullyLoaded = false


    lateinit var adapter: NewsAdapter
    lateinit var presenter: NewsPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        _errorBinding = LayoutErrorBinding.bind(binding.error.root)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        initRecyclerView()
        initViews()
    }

    private fun initPresenter() {
        presenter = NewsPresenter(this)
        presenter.getNews()
    }

    private fun initViews() {
        errorBinding.btnRetry.setOnClickListener {
            presenter.getNews()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.articles.clear()
            adapter.notifyDataSetChanged()
            presenter.refreshArticles()
        }
    }

    private fun initRecyclerView() {
        adapter = NewsAdapter {
            val newsUrl = it.url
            val customTab = CustomTabsIntent.Builder().build()
            customTab.launchUrl(requireContext(), Uri.parse(newsUrl))
        }
        binding.recyclerNews.adapter = adapter
        binding.recyclerNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    if (!isLoading && !isFullyLoaded) {
                        presenter.getNextPage()
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _errorBinding = null
    }

    override fun isLoading() {
        isLoading = true
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun articlesLoaded(articles: List<NewsResponse.Article>, totalArticles: Int?) {
        isLoading = false
        binding.swipeRefreshLayout.isRefreshing = false
        adapter.articles.addAll(articles)
        adapter.notifyDataSetChanged()
        if (adapter.articles.size >= totalArticles ?: 0) {
            isFullyLoaded = true
        }
    }

    override fun error(error: String, isFirstRequest: Boolean) {
        isLoading = false
        if (isFirstRequest) {
            errorBinding.layoutError.visibility = VISIBLE
            errorBinding.txtError.text = error
        } else {
            Snackbar.make(binding.root, error, Snackbar.LENGTH_LONG).setAction("RETRY") {
                presenter.getNews()
            }.show()
        }
    }
}