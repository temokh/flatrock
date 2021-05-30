package com.raywenderlich.flatrock.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.raywenderlich.flatrock.ImgListener
import com.raywenderlich.flatrock.R
import com.raywenderlich.flatrock.data.result.MovieResult
import com.raywenderlich.flatrock.databinding.FragmentTopBinding
import com.raywenderlich.flatrock.network.Network
import com.raywenderlich.flatrock.util.UiErrorInterface
import com.raywenderlich.flatrock.util.handleNetworkError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class PopularFragment : Fragment(), UiErrorInterface {



    private  var binding: FragmentTopBinding? =null

    private var movieList = mutableListOf<MovieResult>()

    private lateinit var adapter: PopularAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (binding == null){
            binding = FragmentTopBinding.inflate(inflater, container, false)
            init()
        }
        return binding?.root
    }

    private fun init(){
        adapter = PopularAdapter(movieList,context,object : ImgListener{
            override fun onClickimg(position: Int) {
                setFragmentResult("requestKey", bundleOf("bundleKey" to movieList[position].id))
                findNavController().navigate(R.id.action_navigation_home_to_detailFragment)
            }

        })
        val layoutManager = GridLayoutManager(context,2)
        binding?.recyclerview?.layoutManager = layoutManager
        binding?.recyclerview?.adapter = adapter
        loadMovie()
    }



    private fun loadMovie(){
        lifecycleScope.launchWhenCreated {
            try {
                val data = withContext(Dispatchers.IO){
                    Network.movieService.getPopular()
                }
                movieList.addAll(data.results)

                adapter.notifyDataSetChanged()
            }catch (e: Exception){
                handleNetworkError(e)

            }
        }
    }

    override fun onNoInternet() {
        Toast.makeText(context,"No internet connection",Toast.LENGTH_SHORT).show()
    }

    override fun onServerError(message: String) {
        Toast.makeText(context,"$message error",Toast.LENGTH_SHORT).show()
    }




}