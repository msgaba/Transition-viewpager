package com.example.transition

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.transition.databinding.ViewTransitionViewpagerBinding
import com.example.transition.model.TransitionItem


/**
 * Created by Ankita
 */
class TransitionViewPager: LinearLayout {

    private var binding: ViewTransitionViewpagerBinding =
        ViewTransitionViewpagerBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var mContext: Context
    private var transitionList: List<TransitionItem> = arrayListOf()
    private lateinit var mAdapter: TransitionViewPagerAdapter
    private val slideHandler = android.os.Handler()
    private lateinit var slideRunnable: Runnable

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun bind(context: Context, list: List<TransitionItem>) {
        mContext = context
        transitionList = list
        viewSetup()
    }

    private fun viewSetup() {
        mAdapter = TransitionViewPagerAdapter(mContext,
            transitionList as MutableList<TransitionItem>, binding.viewpager)
        binding.viewpager.apply {
            adapter = mAdapter
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
            val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(40))
            val width = context.screenWidth()
            val pageMarginPx = width * 0.23
            transformer.addTransformer(ZoomOutAndMovingPageTransformer(pageMarginPx.toInt()))
            setPageTransformer(transformer)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if(position > 0) {
                        slideHandler.removeCallbacks(slideRunnable)
                        slideHandler.postDelayed(slideRunnable, 2000)
                    } else currentItem = 1
                }
            })
            slideRunnable = Runnable {
                /* auto movement for viewpager */
                currentItem += 1
            }
        }
    }

    fun unBind() {
        slideHandler.removeCallbacks(slideRunnable)
    }

    fun resume() {
        slideHandler.postDelayed(slideRunnable, 2000)
    }
}