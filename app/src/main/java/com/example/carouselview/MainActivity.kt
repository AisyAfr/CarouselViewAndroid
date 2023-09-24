package com.example.carouselview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.carouselview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageSlideAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var dots: ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list.add(
            ImageData(
                "https://asset-a.grid.id/crop/0x0:0x0/x/photo/2022/06/23/fc56fac8-1e52-45c4-a1d9-b57c0cac-20220623112609.jpeg"
            )
        )

        list.add(
            ImageData(
                "https://images.tokopedia.net/blog-tokopedia-com/uploads/2021/01/bunga-bangkai.jpg"
            )
        )

        list.add(
            ImageData(
                "https://asset.kompas.com/crops/Uqk7JQP5VPhMpsvZllbSyMYmYpU=/0x86:1440x1046/750x500/data/photo/2022/08/08/62f0e02261ede.png"
            )
        )

        list.add(
            ImageData(
                "https://cdnwpedutorenews.gramedia.net/wp-content/uploads/2022/03/08104023/image001-9-810x540.jpg"
            )
        )

        adapter = ImageSlideAdapter(list)
        binding.viewPager.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })
    }

    private fun selectedDot(position: Int){
        for (i in 0 until list.size){
            if (i == position)
                dots[i].setTextColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_primary))
            else
                dots[i].setTextColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_secondary))
        }
    }

    private fun setIndicator() {
        for (i in 0 until list.size){
            dots.add(TextView(this))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                dots[i].text = Html.fromHtml("&#9679")
            }
            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }
    }
}