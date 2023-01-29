package com.example.transition

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.transition.databinding.ItemTransitionViewpagerBinding
import com.example.transition.model.TransitionItem

/**
 * Created by Ankita
 */
class TransitionViewPagerAdapter(
    private val context: Context,
    private var mList: MutableList<TransitionItem>,
    private val viewPager: ViewPager2
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemTransitionViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransitionViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as TransitionViewPagerViewHolder
        viewHolder.bind(context, mList[position])
        if (position == mList.size - 2)
            viewPager.post(listRunnable)
    }

    override fun getItemCount() = mList.size

    private val listRunnable = Runnable {
        mList.addAll(mList)
        notifyDataSetChanged()
    }
}