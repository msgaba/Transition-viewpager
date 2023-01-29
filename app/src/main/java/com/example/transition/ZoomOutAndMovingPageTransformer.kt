package com.example.transition

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

/**
 * Created by Ankita
 * class performing zooming out for active fragment
 */
class ZoomOutAndMovingPageTransformer(private val pageMarginPx: Int) :
    ViewPager2.PageTransformer {

    private val MIN_SCALE = 0.55f

    override fun transformPage(page: View, position: Float) {
        if (position <= 1) { // [ -1,1 ]
            val scaleFactor = MIN_SCALE.coerceAtLeast(1 - abs(position))
            // Scale the page down ( between MIN_SCALE and 1 )
            page.scaleX = scaleFactor
            page.scaleY = scaleFactor
            val offset = position * -(3 * pageMarginPx)
            page.translationX = offset

        }
    }
}