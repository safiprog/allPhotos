package com.example.allpicture

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allpicture.dat.Result

class imageAdaptor(val context: Context, val imagedata: List<Result>,val listener:Listener) :
    RecyclerView.Adapter<imageAdaptor.imageViewHoder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): imageViewHoder {
        val view=LayoutInflater.from(context).inflate(R.layout.image_view,parent,false)
        return imageViewHoder(view)
    }

    override fun onBindViewHolder(holder: imageViewHoder, position: Int) {
        val imageinfo=imagedata[position]
        Glide.with(context).load(imageinfo.urls.regular).into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return imagedata.size
    }

    inner class imageViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        init {
            imageView.setOnClickListener{
                listener.onClickListener(adapterPosition)
            }
        }

    }
}