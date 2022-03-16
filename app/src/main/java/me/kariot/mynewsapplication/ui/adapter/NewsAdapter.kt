package me.kariot.mynewsapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.kariot.mynewsapplication.databinding.RecyclerNewsItemBinding
import me.kariot.mynewsapplication.extensions.loadImage
import me.kariot.mynewsapplication.model.newsModel.NewsResponse
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(val onItemClick: (NewsResponse.Article) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsVH>() {

    var articles = ArrayList<NewsResponse.Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val binding =
            RecyclerNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsVH(binding)
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        holder.bind(articles[position])
        holder.itemView.setOnClickListener {
            onItemClick(articles[position])
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class NewsVH(private val bindingView: RecyclerNewsItemBinding) :
        RecyclerView.ViewHolder(bindingView.root) {

        fun bind(article: NewsResponse.Article) {


            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yy HH:mm", Locale.getDefault())

            val date = inputFormat.parse(article.publishedAt)
            val formattedDate = outputFormat.format(date)
            bindingView.apply {
                txtTime.text = formattedDate
                imgImage.loadImage(article.urlToImage ?: "")
                txtTitle.text = article.title
                txtContent.text = article.content
                txtSource.text = article.author
            }
        }

    }
}