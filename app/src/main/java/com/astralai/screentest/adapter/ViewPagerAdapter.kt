package com.astralai.screentest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.astralai.screentest.R
import com.astralai.screentest.model.ViewPagerItem

/**
 * File created by by vidyesh churi on 11/9/2020 4:04 PM
 */
class ViewPagerAdapter(private val itemList: List<ViewPagerItem>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view_pager, parent, false)
        return ViewPagerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.layoutBackground.setBackgroundResource(currentItem.colorResource)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layoutBackground: ConstraintLayout = itemView.findViewById(R.id.item_view_pager_surface)
    }
}