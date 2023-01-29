package com.example.transition

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.transition.databinding.ItemTransitionViewpagerBinding
import com.example.transition.model.TransitionItem

/**
 * Created by Ankita
 */
class TransitionViewPagerViewHolder(private val binding: ItemTransitionViewpagerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, item: TransitionItem) {
        binding.apply {
            header.text = item.header
            subHeader.text = item.subHeader
            image.setImageResource(item.image)
        }
    }
}