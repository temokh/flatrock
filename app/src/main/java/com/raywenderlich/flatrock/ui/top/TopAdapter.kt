package com.raywenderlich.flatrock.ui.top

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raywenderlich.flatrock.ImgListener
import com.raywenderlich.flatrock.data.result.MovieResult
import com.raywenderlich.flatrock.databinding.ItemMoviesBinding

class TopAdapter(val data: List<MovieResult>, val context: Context?,val imgListener: ImgListener) : RecyclerView.Adapter<TopAdapter.ViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }



    override fun getItemCount()= data.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(private val binding: ItemMoviesBinding): RecyclerView.ViewHolder(binding.root),View.OnClickListener{
        fun bind(){

            binding.movieImg.setOnClickListener(this)

            Glide.with(context!!).load(img200+data[adapterPosition].posterPath)
                .into(binding.movieImg)



        }

        override fun onClick(v: View?) {
            if (v == binding.movieImg){
                imgListener.onClickimg(adapterPosition)
            }
        }

    }

    companion object{
        const val img200 = "https://image.tmdb.org/t/p/w500/"
    }


}