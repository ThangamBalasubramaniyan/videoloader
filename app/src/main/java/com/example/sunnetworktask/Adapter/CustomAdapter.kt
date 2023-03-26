package com.example.sunnetworktask.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sunnetworktask.Data.ImageList
import com.example.sunnetworktask.R

class CustomAdapter(var list: ImageList, var itemClick: adapterClickListener) : RecyclerView.Adapter<CustomAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        var view: View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_view, parent, false)
        return RecyclerViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    interface adapterClickListener {
        fun getItem(position: Int)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindData(list, position)
    }

    class RecyclerViewHolder(itemView: View, var itemClick: adapterClickListener) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.findViewById(R.id.image)
        fun bindData(userList: ImageList, position: Int) {
            Glide.with(itemView.getContext()).load(userList.get(position).url).into(image)
            itemView.setOnClickListener(View.OnClickListener {
                itemClick.getItem(adapterPosition)
            })
        }
    }
}