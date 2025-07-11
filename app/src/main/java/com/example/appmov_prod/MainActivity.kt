package com.example.appmov_prod

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var handler: Handler
    private val delay = 5000L
    private var page = 0

    private val items = listOf(
        OnboardingItem(R.drawable.slider, "Recycle from Home", "Find the nearest recycling drop-off points with real-time updates."),
        OnboardingItem(R.drawable.slider2, "Nearby Recycling Stations", "Find the nearest recycling drop-off points with real-time updates.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = OnboardingAdapter(items)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        findViewById<MaterialButton>(R.id.btnLogin).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.btnRegister).setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        handler = Handler(Looper.getMainLooper())
        autoSlide()
    }

    private fun autoSlide() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (page == items.size) page = 0
                viewPager.setCurrentItem(page++, true)
                handler.postDelayed(this, delay)
            }
        }, delay)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
