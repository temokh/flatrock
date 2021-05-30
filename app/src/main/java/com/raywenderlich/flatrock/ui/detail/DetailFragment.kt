package com.raywenderlich.flatrock.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.raywenderlich.flatrock.R
import com.raywenderlich.flatrock.databinding.FragmentDetailBinding
import com.raywenderlich.flatrock.network.Network
import com.raywenderlich.flatrock.storage.FavMovies
import com.raywenderlich.flatrock.storage.FavMoviesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private lateinit var favMoviesViewModel: FavMoviesViewModel

    private lateinit var currentFavMovies: FavMovies

    private val imgAdress = "https://image.tmdb.org/t/p/w500/"


    private var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey"){requestKey, bundle ->
        id = bundle.getInt("bundleKey").toString()
            loadDate()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView2.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.button2.setOnClickListener {
            favMoviesViewModel = ViewModelProvider(this).get(FavMoviesViewModel::class.java)
            favMoviesViewModel.addMovies(currentFavMovies)
        }

    }


    private fun loadDate(){
        lifecycleScope.launchWhenCreated {
            try {
                val data = withContext(Dispatchers.IO){
                    Network.movieService.getDetails(id)
                }

                Glide.with(requireContext()).load(imgAdress+data.posterPath)
                    .into(binding.imageView)
                binding.apply {
                    titleTxt.text = data.title
                    originalTitleTxt.text = data.originalTitle
                    overviewTxt.text = data.overview
                    ratingTxt.text = data.voteCount.toString()
                    realiseDateTxt.text = data.releaseDate
                    currentFavMovies = FavMovies(data.id!!,data.posterPath!!)

                }

            }catch (e: Exception){

                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show()
            }
        }
    }


}