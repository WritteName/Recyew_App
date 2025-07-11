package com.example.appmov_prod

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Fragmento por defecto (puedes cambiarlo por el de mapa, reciclaje, etc.)
        loadFragment(ProfileFragment())

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // Botón flotante
        val fab = findViewById<FloatingActionButton>(R.id.fab_recycling)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}
