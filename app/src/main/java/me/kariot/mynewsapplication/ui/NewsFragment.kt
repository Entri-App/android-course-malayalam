package me.kariot.mynewsapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    }

    private fun initRecyclerView() {
        adapter = NewsAdapter()
        binding.recyclerNews.adapter = adapter
    }

    private fun fetchLatestNews() {
        RetrofitInstance.newsApi.getLatestNews(
            Constants.NEWS_COUNTRY_SOURCE,
            Constants.API_KEY,
            pageSize,
            pageCount
        ).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {

                if (response.isSuccessful) {
                    errorBinding.layoutError.visibility = View.GONE
                    val articles = response.body()?.articles ?: emptyList()
                    adapter.articles.addAll(articles)
                    adapter.notifyDataSetChanged()
                } else {
                    showError("The request was failed")
                }

            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                t.printStackTrace()
                showError("There was a network failure.")
            }

        })
    }

    private fun showError(s: String) {
        errorBinding.layoutError.visibility = View.VISIBLE
        errorBinding.txtError.text = s
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _errorBinding = null
    }
}