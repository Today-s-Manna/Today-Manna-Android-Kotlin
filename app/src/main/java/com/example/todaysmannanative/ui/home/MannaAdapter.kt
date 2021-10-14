package com.example.todaysmannanative.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todaysmannanative.R

class MannaAdapter(private val context: Context) : RecyclerView.Adapter<MannaAdapter.ViewHolder>() {

    var datas = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_manna,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.mannaTextView)

        fun bind(item: String) {
            txtName.text = item
        }
    }

    fun setData(data : ArrayList<String>){
        datas = data
        notifyDataSetChanged()
    }
}