package me.kariot.mynewsapplication.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import me.kariot.mynewsapplication.api.RetrofitInstance
import me.kariot.mynewsapplication.databinding.FragmentNewsBinding
import me.kariot.mynewsapplication.databinding.LayoutErrorBinding
import me.kariot.mynewsapplication.model.newsModel.NewsResponse
import me.kariot.mynewsapplication.ui.adapter.NewsAdapter
import me.kariot.mynewsapplication.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private var _errorBinding: LayoutErrorBinding? = null
    private val errorBinding get() = _errorBinding!!

    private var pageSize = 20
    private var pageCount = 1

    private var isLoading = false
    private var isFullyLoaded = false


    lateinit var adapter: NewsAdapter

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
        initRecyclerView()
        initViews()
        fetchLatestNews()
    }

    private fun initViews() {
        errorBinding.btnRetry.setOnClickListener {
            fetchLatestNews()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.articles.clear()
            adapter.notifyDataSetChanged()
            pageCount = 1
            fetchLatestNews()
        }
    }

    private fun initRecyclerView() {
        adapter = NewsAdapter {
            val newsUrl = it.url
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsUrl))
//            startActivity(intent)
            val customTab = CustomTabsIntent.Builder().build()
            customTab.launchUrl(requireContext(), Uri.parse(newsUrl))
        }
        binding.recyclerNews.adapter = adapter
        binding.recyclerNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    if (!isLoading && !isFullyLoaded) {
                        pageCount += 1
                        fetchLatestNews()
                    }
                }
            }
        })
    }

    private fun fetchLatestNews() {
        isLoading = true
        errorBinding.layoutError.visibility = View.GONE
        binding.swipeRefreshLayout.isRefreshing = true
        RetrofitInstance.newsApi.getLatestNews(
            Constants.NEWS_COUNTRY_SOURCE,
            Constants.API_KEY,
            pageSize,
            pageCount
        ).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                binding.swipeRefreshLayout.isRefreshing = false
                isLoading = false
                if (response.isSuccessful) {
                    errorBinding.layoutError.visibility = View.GONE
                    val articles = response.body()?.articles ?: emptyList()
                    adapter.articles.addAll(articles)
                    adapter.notifyDataSetChanged()
                    if (adapter.articles.size == response.body()?.totalResults) {
                        isFullyLoaded = true
                    }
                } else {
                    showError("The request was failed")
                }

            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                t.printStackTrace()
                isLoading = false
                binding.swipeRefreshLayout.isRefreshing = false
                showError("There was a network failure.")
            }

        })
    }

    private fun showError(s: String) {
        if (pageCount > 1) {
            Snackbar.make(binding.root, s, Snackbar.LENGTH_LONG).setAction("RETRY") {
                fetchLatestNews()
            }.show()
            return
        }
        errorBinding.layoutError.visibility = View.VISIBLE
        errorBinding.txtError.text = s
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _errorBinding = null
    }
}