package com.example.kotlinstart3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppInstalledAdapter(itemList: List<AppModel>) :
    RecyclerView.Adapter<AppInstalledAdapter.ViewHolder>() {


    private val teriak = mutableListOf<AppModel>().apply { addAll(itemList) }

    fun addItems(items: List<AppModel>) {
        teriak.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_app_list , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val AppModel = teriak[position]
        holder.imageView.setImageDrawable(AppModel.appIcon)
        holder.textView.text = AppModel.appName
    }

    override fun getItemCount(): Int {
        return teriak.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }



}