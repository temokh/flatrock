package com.raywenderlich.flatrock.ui.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raywenderlich.flatrock.ImgListener
import com.raywenderlich.flatrock.databinding.ItemMoviesBinding
import com.raywenderlich.flatrock.storage.FavMovies

class FavMoviesAdapter(val context: Context?, val imgListener: ImgListener):RecyclerView.Adapter<FavMoviesAdapter.MyViewHolder>() {

    private var favMovieList = emptyList<FavMovies>()

    inner class MyViewHolder(private val binding: ItemMoviesBinding):RecyclerView.ViewHolder(binding.root),View.OnClickListener{

        fun bind(){

            binding.movieImg.setOnClickListener(this)

            Glide.with(context!!).load(img200+favMovieList[adapterPosition].img)
                .into(binding.movieImg)

        }

        override fun onClick(v: View?) {
            if (v == binding.movieImg){
                imgListener.onClickimg(favMovieList[adapterPosition].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = favMovieList.size


    companion object{
        const val img200 = "https://image.tmdb.org/t/p/w500/"
    }

    fun setData(favMovies: List<FavMovies>){
        this.favMovieList = favMovies
        notifyDataSetChanged()
    }


}