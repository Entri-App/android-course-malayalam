package com.example.myapplication

import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupUI()
    }

    fun setupUI() {
        /* binding.videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.demo_video))
         binding.videoView.start()*/

        val controller = MediaController(this)
        controller.setMediaPlayer(binding.videoView)
        binding.videoView.setMediaController(controller)

        val videoUrl =
            "https://vod-progressive.akamaized.net/exp=1639435487~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F2714%2F22%2F563574580%2F2664906044.mp4~hmac=db93cb5fe0b790b0ea021dff5aaa6e1e113494dcb0fd8c6e924b76a8582ac37b/vimeo-prod-skyfire-std-us/01/2714/22/563574580/2664906044.mp4?filename=pexels-%E5%AE%87%E8%88%AA-%E9%92%B1-8354985.mp4"
        binding.videoView.setVideoPath(videoUrl)
        binding.videoView.start()
    }


}