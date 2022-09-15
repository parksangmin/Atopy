package com.sangmin.atopy.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sangmin.atopy.R
import com.sangmin.atopy.data.Model

// String -> Model 로 교체
class CategoryRVAdapter(val context: Context,val items : ArrayList<Model>) : RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>() {

    interface ItemClick {
        fun onClick(view : View, position: Int)


    }
    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category1_item, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemClick != null){
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
        holder.bindItems(items[position])

    }


//    아이템의 갯수가 몇개인지
   override fun getItemCount(): Int {
        return items.size

    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        // String -> Model 로 교체
        fun bindItems(item : Model) {

            val Maintitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageView  = itemView.findViewById<ImageView>(R.id.ImgView)


            Maintitle.text = item.title
            Glide.with(context)
                .load(item.imageUrl)
                .into(imageView)


        }
    }
}