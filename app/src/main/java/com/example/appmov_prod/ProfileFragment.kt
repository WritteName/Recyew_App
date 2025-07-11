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
            val initialHeight = view.height
            val animator = ValueAnimator.ofInt(initialHeight, 0)
            animator.addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Int
                view.layoutParams.height = value
                view.requestLayout()
            }
            animator.duration = 300
            animator.doOnEnd {
                view.visibility = View.GONE
                view.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT // Reset
            }
            animator.start()
        } else {
            view.visibility = View.VISIBLE

            // Forzar medición antes de animar
            view.measure(
                View.MeasureSpec.makeMeasureSpec((view.parent as View).width, View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            val targetHeight = view.measuredHeight

            view.layoutParams.height = 0
            val animator = ValueAnimator.ofInt(0, targetHeight)
            animator.addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Int
                view.layoutParams.height = value
                view.requestLayout()
            }
            animator.duration = 300
            animator.doOnEnd {
                view.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT // Asegura ajuste automático
            }
            animator.start()
        }
    }

}
