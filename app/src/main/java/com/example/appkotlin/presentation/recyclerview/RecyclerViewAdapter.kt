package com.example.appkotlin.presentation.recyclerview

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appkotlin.R
import com.example.appkotlin.data.remote.RecyclerData
import kotlinx.android.synthetic.main.recycleview_row.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>()
{
    var items = ArrayList<RecyclerData>()

    fun setListData(data: ArrayList<RecyclerData>){
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_row, parent,false)

        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        val tvTitle = view.tvTitle
        val tvDesc = view.tvDesc
        val imageThumb = view.imageThumb

        fun bind (data: RecyclerData){
            tvTitle.text = data.name
            if(!TextUtils.isEmpty(data.description)){
                tvDesc.text = data.description
            }else{
                tvDesc.text = "No description"
            }
            val url = data.owner.avatar_url
            Glide.with(imageThumb)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.default_thumb)
                .error(R.drawable.default_thumb)
                .fallback(R.drawable.default_thumb)
                .into(imageThumb)

        }

    }
}