package com.example.todaysmannanative.ui.mccheyne

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.todaysmannanative.R
import com.example.todaysmannanative.databinding.FragmentMccheyneBinding

class MccheyneContentFragment1 : Fragment() {
    private lateinit var binding: FragmentMccheyneBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMccheyneBinding.inflate(inflater, container, false)



        return binding.root
    }
}
