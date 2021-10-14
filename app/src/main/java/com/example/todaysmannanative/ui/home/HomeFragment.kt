package com.example.todaysmannanative.ui.home

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todaysmannanative.dataManagers.MannaManager
import com.example.todaysmannanative.databinding.FragmentHomeBinding
import com.example.todaysmannanative.models.MannaItem
import com.example.todaysmannanative.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var _context: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(_context)
        recyclerView.adapter = MannaAdapter(_context)

        homeViewModel.contentsLiveData.observe(viewLifecycleOwner, Observer {
            (recyclerView.adapter as MannaAdapter).setData(it)
        })

        LocalBroadcastManager.getInstance(_context).registerReceiver(
            mBroadcastReceiver,
            IntentFilter("mannaData")
        );
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _context = context
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.updateValue(MannaManager.mannaItem)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        LocalBroadcastManager.getInstance(_context).unregisterReceiver(mBroadcastReceiver);
    }

    private val mBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("NotifyDataSetChanged")
        override fun onReceive(context: Context?, intent: Intent?) {
            val item = intent!!.getParcelableExtra<MannaItem>("item")

            if (item != null) {
                homeViewModel.updateValue(item)
            }
        }
    }
}