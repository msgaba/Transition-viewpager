package com.example.transition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.transition.databinding.ActivityMainBinding
import com.example.transition.model.TransitionItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private fun transitionList() = arrayListOf(
        TransitionItem(R.drawable.ic_food, getString(R.string.transition_header_1), getString(R.string.transition_sub_header_1)),
        TransitionItem(R.drawable.ic_delivery, getString(R.string.transition_header_2), getString(R.string.transition_sub_header_2)),
        TransitionItem(R.drawable.ic_payment, getString(R.string.transition_header_3), getString(R.string.transition_sub_header_3)),
        TransitionItem(R.drawable.ic_customer_support, getString(R.string.transition_header_4), getString(R.string.transition_sub_header_4)),
    )

    override fun onStop() {
        super.onStop()
        binding.viewpager.unBind()
    }

    override fun onResume() {
        super.onResume()
        binding.viewpager.resume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewpager.bind(this, transitionList())
    }
}