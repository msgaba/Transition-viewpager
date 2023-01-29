package com.example.transition

import android.content.Context
import android.graphics.Point
import android.view.WindowManager

/**
 * Created by Ankita
 */

/* returns screen width */
fun Context.screenWidth(): Int {
    val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    val point = Point()
    display.getSize(point)
    return point.x
}