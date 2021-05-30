package com.raywenderlich.flatrock.ui.top

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.raywenderlich.flatrock.ImgListener
import com.raywenderlich.flatrock.R
import com.raywenderlich.flatrock.data.result.MovieResult
import com.raywenderlich.flatrock.databinding.FragmentPopularBinding
import com.raywenderlich.flatrock.network.Network
import com.raywenderlich.flatrock.util.UiErrorInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class TopFragment : Fragment(), UiErrorInterface {


    private  var binding: FragmentPopularBinding? = null

    private var movieList = mutableListOf<MovieResult>()

    private lateinit var adapter: TopAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        if (binding == null){
            binding = FragmentPopularBinding.inflate(layoutInflater)
            init()

        }
        return binding?.root
    }


    private fun init(){
        adapter = TopAdapter(movieList,context,object : ImgListener{
            override fun onClickimg(position: Int) {
                setFragmentResult("requestKey", bundleOf("bundleKey" to movieList[position].id))
                findNavController().navigate(R.id.action_navigation_dashboard_to_detailFragment)
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
                    Network.movieService.getRated()
                }
                movieList.addAll(data.results)

                adapter.notifyDataSetChanged()
            }catch (e: Exception){
                Toast.makeText(context,e.toString(), Toast.LENGTH_LONG).show()
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