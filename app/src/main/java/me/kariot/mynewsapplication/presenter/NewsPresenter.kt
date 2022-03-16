package me.kariot.mynewsapplication.presenter

import me.kariot.mynewsapplication.api.RetrofitInstance
import me.kariot.mynewsapplication.model.newsModel.NewsResponse
import me.kariot.mynewsapplication.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsPresenter(private val callback: NewsView) {

    private var pageSize = 20
    private var pageCount = 1

    fun getNews() {
        callback.isLoading()
        RetrofitInstance.newsApi.getLatestNews(
            Constants.NEWS_COUNTRY_SOURCE,
            Constants.API_KEY,
            pageSize,
            pageCount
        ).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    callback.articlesLoaded(articles, response.body()?.totalResults)
                } else {
                    callback.error("an unexpected error occurred..!", pageCount == 1)
                }

            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                t.printStackTrace()
                callback.error("a network failure occurred..!", pageCount == 1)
            }

        })
    }

    fun refreshArticles() {
        pageCount = 1
        getNews()
    }

    fun getNextPage() {
        pageCount += 1
        getNews()
    }

}

interface NewsView {
    fun isLoading()
    fun articlesLoaded(articles: List<NewsResponse.Article>, totalArticles: Int?)
    fun error(error: String, isFirstRequest: Boolean)
}