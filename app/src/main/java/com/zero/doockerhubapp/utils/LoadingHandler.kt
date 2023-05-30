package com.zero.doockerhubapp.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.zero.doockerhubapp.utils.Constants.Companion.ANIMATION_URL

//Requires the parent constraintLayout
class LoadingHandler(val context: Context, private val constraintLayout: ConstraintLayout) {
    private val animationView: LottieAnimationView = LottieAnimationView(context)

    fun startAnimation() {
        for (i in 0..constraintLayout.childCount) {
            if (constraintLayout.getChildAt(i) != null) {
                constraintLayout.getChildAt(i).visibility = View.GONE
            }
        }
        animationView.setAnimationFromUrl(ANIMATION_URL)
        constraintLayout.addView(animationView)
        animationView.layoutParams.height = MATCH_PARENT
        animationView.layoutParams.width = MATCH_PARENT
        animationView.visibility = View.VISIBLE
        animationView.playAnimation()
    }

    fun stopAnimation() {
        animationView.visibility = View.GONE
        constraintLayout.removeView(animationView)
        for (i in 0..constraintLayout.childCount) {
            if (constraintLayout.getChildAt(i) != null) {
                Log.d("er", constraintLayout.getChildAt(i).toString())
                constraintLayout.getChildAt(i).visibility = View.VISIBLE
            }
        }
    }
}