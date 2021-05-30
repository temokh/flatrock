package com.raywenderlich.flatrock.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.raywenderlich.flatrock.ImgListener
import com.raywenderlich.flatrock.R
import com.raywenderlich.flatrock.databinding.FragmentFavoriteBinding
import com.raywenderlich.flatrock.storage.FavMoviesViewModel
import com.raywenderlich.flatrock.util.UiErrorInterface


class FavoriteFragment : Fragment() {


    private lateinit var binding: FragmentFavoriteBinding

    private lateinit var adapter: FavMoviesAdapter

    private lateinit var favMoviesViewModel: FavMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        init()

        favMoviesViewModel = ViewModelProvider(this).get(FavMoviesViewModel::class.java)
        favMoviesViewModel.readAlldata.observe(viewLifecycleOwner, Observer { FavMovies ->
            adapter.setData(FavMovies)
        })


        return binding.root



    }


    private fun init(){


        adapter = FavMoviesAdapter(context,object :ImgListener{
            override fun onClickimg(position: Int) {
                setFragmentResult("requestKey", bundleOf("bundleKey" to position))
                findNavController().navigate(R.id.action_navigation_notifications_to_detailFragment)
            }

        })
        val layoutManager = GridLayoutManager(context,2)
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = adapter

    }



}