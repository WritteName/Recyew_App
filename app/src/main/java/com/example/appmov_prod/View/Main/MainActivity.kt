package com.example.appmov_prod.View.Main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.appmov_prod.View.Login.LoginActivity
import com.example.appmov_prod.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.example.appmov_prod.ViewModel.MainViewModel
class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: OnboardingAdapter
    private lateinit var handler: Handler
    private val delay = 5000L
    private var page = 0

    private lateinit var btnDirIngreso: MaterialButton
    private lateinit var tabLayout: TabLayout

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupClickListeners()
        InitOnboarding()

        handler = Handler(Looper.getMainLooper())
        autoSlide()
    }

    private fun initViews() {
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        btnDirIngreso = findViewById<MaterialButton>(R.id.btnInit)
    }

    private fun setupClickListeners() {
        btnDirIngreso.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun InitOnboarding() {
        viewModel.onboardingItems.observe(this) { items ->
            adapter = OnboardingAdapter(items)
            viewPager.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
        }
    }

    private fun autoSlide() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (::adapter.isInitialized && adapter.itemCount > 0) {
                    if (page == adapter.itemCount) page = 0
                    viewPager.setCurrentItem(page++, true)
                }
                handler.postDelayed(this, delay)
            }
        }, delay)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}