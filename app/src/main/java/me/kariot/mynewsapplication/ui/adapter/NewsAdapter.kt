package me.kariot.mynewsapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.kariot.mynewsapplication.databinding.RecyclerNewsItemBinding
import me.kariot.mynewsapplication.extensions.loadImage
import me.kariot.mynewsapplication.model.newsModel.NewsResponse

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsVH>() {

    var articles = ArrayList<NewsResponse.Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val binding =
            RecyclerNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsVH(binding)
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class NewsVH(private val bindingView: RecyclerNewsItemBinding) :
        RecyclerView.ViewHolder(bindingView.root) {

        fun bind(article: NewsResponse.Article) {
            bindingView.imgImage.loadImage(article.urlToImage)
            bindingView.txtTitle.text = article.title
            bindingView.txtContent.text = article.content
            bindingView.txtSource.text = article.author
            bindingView.txtTime.text = article.publishedAt
        }

    }
}