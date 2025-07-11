package com.example.appmov_prod

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutUserInfo = view.findViewById<LinearLayout>(R.id.layoutUserInfoContent)
        view.findViewById<TextView>(R.id.tvToggleUserInfo).setOnClickListener {
            toggleVisibility(layoutUserInfo)
        }

        val datosPersonales = view.findViewById<LinearLayout>(R.id.layoutDatosPersonales)
        view.findViewById<TextView>(R.id.tvToggleDatosPersonales).setOnClickListener {
            toggleVisibility(datosPersonales)
        }

        val datosContacto = view.findViewById<LinearLayout>(R.id.layoutDatosContacto)
        view.findViewById<TextView>(R.id.tvToggleContacto).setOnClickListener {
            toggleVisibility(datosContacto)
        }

        val direccion = view.findViewById<LinearLayout>(R.id.layoutDireccion)
        view.findViewById<TextView>(R.id.tvToggleDireccion).setOnClickListener {
            toggleVisibility(direccion)
        }
    }

    private fun toggleVisibility(view: View) {
        if (view.visibility == View.VISIBLE) {
            val initialHeight = view.measuredHeight
            val animator = ValueAnimator.ofInt(initialHeight, 0)
            animator.addUpdateListener {
                val value = it.animatedValue as Int
                view.layoutParams.height = value
                view.requestLayout()
            }
            animator.duration = 300
            animator.doOnEnd { view.visibility = View.GONE }
            animator.start()
        } else {
            view.visibility = View.VISIBLE
            view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            val targetHeight = view.measuredHeight
            view.layoutParams.height = 0

            val animator = ValueAnimator.ofInt(0, targetHeight)
            animator.addUpdateListener {
                val value = it.animatedValue as Int
                view.layoutParams.height = value
                view.requestLayout()
            }
            animator.duration = 300
            animator.start()
        }
    }
}
