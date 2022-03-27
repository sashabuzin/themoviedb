package com.buzinasgeekbrains.themoviedb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.buzinasgeekbrains.themoviedb.R
import com.buzinasgeekbrains.themoviedb.viewmodel.AppState
import com.buzinasgeekbrains.themoviedb.viewmodel.MainViewModel
import com.buzinasgeekbrains.themoviedb.databinding.MainFragmentBinding
import com.buzinasgeekbrains.themoviedb.model.Actor
import com.buzinasgeekbrains.themoviedb.model.Film


const val NOW_PLAYING: String = "NOW_PLAYING"
const val POPULAR: String = "POPULAR"
const val TOP_RATED: String = "TOP_RATED"
const val UPCOMING: String = "UPCOMING"

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    private val nowPlayingAdapter = FilmsFragmentAdapter { film ->
        openDetailsFilmFragment(film)
    }
    private val popularAdapter = FilmsFragmentAdapter { film ->
        openDetailsFilmFragment(film)
    }
    private val topRatedAdapter = FilmsFragmentAdapter { film ->
        openDetailsFilmFragment(film)
    }
    private val upcomingAdapter = FilmsFragmentAdapter { film ->
        openDetailsFilmFragment(film)
    }

    private fun openDetailsFilmFragment(film: Film) {
        val manager = activity?.supportFragmentManager
        if (manager != null) {
            val bundle = Bundle()
            bundle.putParcelable(FilmDetailsFragment.BUNDLE_EXTRA, film)
            manager.beginTransaction()
                .add(R.id.container_main, FilmDetailsFragment.newInstance(bundle))
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nowPlayingRecyclerView.adapter = nowPlayingAdapter
        binding.nowPlayingRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.popularRecyclerView.adapter = popularAdapter
        binding.popularRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.topRatedRecyclerView.adapter = topRatedAdapter
        binding.topRatedRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.upcomingRecyclerView.adapter = upcomingAdapter
        binding.upcomingRecyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getNowPlayingData().observe(viewLifecycleOwner, Observer {
            render(it, NOW_PLAYING)
        })
        viewModel.getPopularData().observe(viewLifecycleOwner, Observer {
            render(it, POPULAR)
        })
        viewModel.getTopRatedData().observe(viewLifecycleOwner, Observer {
            render(it, TOP_RATED)
        })
        viewModel.getUpcomingData().observe(viewLifecycleOwner, Observer {
            render(it, UPCOMING)
        })
        viewModel.getFilmsFromLocalStorage()


    }

    private fun render(state: AppState, s: String) {
        when (state) {
            is AppState.Success<*> -> {
                binding.progressBarcv.visibility = View.GONE

                when (s) {
                    NOW_PLAYING -> nowPlayingAdapter.setFilm(state.data as List<*>)
                    POPULAR -> popularAdapter.setFilm(state.data as List<*>)
                    TOP_RATED -> topRatedAdapter.setFilm(state.data as List<*>)
                    UPCOMING -> upcomingAdapter.setFilm(state.data as List<*>)
                }

            }
            is AppState.Error -> {
                binding.progressBarcv.visibility = View.VISIBLE
                viewModel.getFilmsFromLocalStorage()
            }
            is AppState.Loading -> {
                binding.progressBarcv.visibility = View.VISIBLE
            }
        }

    }


    private fun removeAllListener() {
        nowPlayingAdapter.removeListener()
        popularAdapter.removeListener()
        topRatedAdapter.removeListener()
        upcomingAdapter.removeListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeAllListener()
        _binding = null
    }
}