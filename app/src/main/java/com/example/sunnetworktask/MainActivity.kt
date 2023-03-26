package com.example.sunnetworktask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.sunnetworktask.Adapter.CarouselAdapter
import com.example.sunnetworktask.Adapter.CustomAdapter
import com.example.sunnetworktask.VideoPage.PlayActivity
import com.example.sunnetworktask.ViewModel.MainViewModel
import com.example.sunnetworktask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CustomAdapter.adapterClickListener {
    private lateinit var binding: ActivityMainBinding
    var url: String? = ""
    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: CarouselAdapter
    lateinit var imageList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPager = findViewById(R.id.idViewPager)

        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.carouselmovie3
        imageList = imageList + R.drawable.carouselmovie2
        imageList = imageList + R.drawable.carousalmovie1

        viewPagerAdapter = CarouselAdapter(this@MainActivity, imageList)

        viewPager.adapter = viewPagerAdapter
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

//        var recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewModel.getImages()
        viewModel.myResponseList.observe(this, Observer {
            binding.recyclerView.adapter = CustomAdapter(it, this)
            binding.recyclerView2.adapter = CustomAdapter(it, this)
        })
    }

    override fun getItem(position: Int) {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.myResponseList.observe(this, {
            url = it.get(position).videoUrl
            println("Thangam ${url}")

            val intent = Intent(this, PlayActivity::class.java)
            intent.putExtra("url", url)
            startActivity(intent)
        })
    }

}