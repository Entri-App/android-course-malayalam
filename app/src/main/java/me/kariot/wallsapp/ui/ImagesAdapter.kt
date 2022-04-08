package me.kariot.wallsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.kariot.wallsapp.databinding.LayoutImageItemBinding
import me.kariot.wallsapp.model.ModelWallpaperResponse
import me.kariot.wallsapp.utils.loadImage

class ImagesAdapter(
    private val onPageEndCallback: () -> Unit
) : RecyclerView.Adapter<ImagesAdapter.ImageVH>() {

    var wallpapers = ArrayList<ModelWallpaperResponse.Photo?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        val binding =
            LayoutImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageVH(binding)
    }

    override fun onBindViewHolder(holder: ImageVH, position: Int) {
        holder.bind(wallpapers[position])
        if (position == wallpapers.size - 1) {
            onPageEndCallback()
        }
    }

    override fun getItemCount() = wallpapers.size

    inner class ImageVH(val bindingView: LayoutImageItemBinding) :
        RecyclerView.ViewHolder(bindingView.root) {
        fun bind(photo: ModelWallpaperResponse.Photo?) {
            bindingView.apply {
                imgMain.loadImage(photo?.src?.large ?: "")
                tvAuthor.text = photo?.photographer
                tvDimensions.text = "${photo?.height ?: "0"}x${photo?.width ?: "0"} "
            }
        }
    }

    fun addData(data: List<ModelWallpaperResponse.Photo?>) {
        this.wallpapers.addAll(data)
        notifyDataSetChanged()
    }
}