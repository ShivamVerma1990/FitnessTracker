package com.candroid.realtracker.intro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.candroid.realtracker.R

class ViewPagerHolder(val context: Context, val item:ArrayList<IntroRec>):RecyclerView.Adapter<ViewPagerHolder.ViewHolders>() {

    class ViewHolders(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val discription: TextView = view.findViewById(R.id.discription)
        val imageId: ImageView = view.findViewById(R.id.imageId)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolders {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.inroo_default, parent, false)
        return ViewHolders(view)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolders, position: Int) {
        val cool = item[position]
        holder.title.text = cool.title
        holder.discription.text = cool.dis
        holder.imageId.setImageResource(cool.image)

    }
}