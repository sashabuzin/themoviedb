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
import com.buzinasgeekbrains.themoviedb.model.Film
import com.buzinasgeekbrains.themoviedb.model.FilmDTO
import com.buzinasgeekbrains.themoviedb.model.ListFilmDTO
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    private val nowPlayingAdapter = FilmsFragmentAdapter { filmDTO ->
        openDetailsFilmFragment(filmDTO)
    }
    private val popularAdapter = FilmsFragmentAdapter { filmDTO ->
        openDetailsFilmFragment(filmDTO)
    }
    private val topRatedAdapter = FilmsFragmentAdapter { filmDTO ->
        openDetailsFilmFragment(filmDTO)
    }
    private val upcomingAdapter = FilmsFragmentAdapter { filmDTO ->
        openDetailsFilmFragment(filmDTO)
    }

    private fun openDetailsFilmFragment(filmDTO: FilmDTO) {
        activity?.supportFragmentManager?.apply {
            beginTransaction()
                .add(R.id.container_main, FilmDetailsFragment.newInstance(Bundle().apply {
                    putParcelable(FilmDetailsFragment.BUNDLE_EXTRA, filmDTO)
                }))
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
        initRecyclerView()
        initViewModels()
        viewModel.getFilmsFromServer()
    }

    enum class MovieSections {
        NOW_PLAYING,
        POPULAR,
        TOP_RATED,
        UPCOMING,
    }

    private fun initViewModels() {
        viewModel.getNowPlayingData().observe(viewLifecycleOwner, Observer {
            render(it, MovieSections.NOW_PLAYING.name)
        })
        viewModel.getPopularData().observe(viewLifecycleOwner, Observer {
            render(it, MovieSections.POPULAR.name)
        })
        viewModel.getTopRatedData().observe(viewLifecycleOwner, Observer {
            render(it, MovieSections.TOP_RATED.name)
        })
        viewModel.getUpcomingData().observe(viewLifecycleOwner, Observer {
            render(it, MovieSections.UPCOMING.name)
        })
    }

    private fun initRecyclerView() {
        binding.nowPlayingRecyclerView.also {
            it.adapter = nowPlayingAdapter
            it.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        }
        binding.popularRecyclerView.also {
            it.adapter = popularAdapter
            it.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        }
        binding.topRatedRecyclerView.also {
            it.adapter = topRatedAdapter
            it.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        }
        binding.upcomingRecyclerView.also {
            it.adapter = upcomingAdapter
            it.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun render(state: AppState, s: String) {
        when (state) {
            is AppState.Success<*> -> {
                binding.progressBarcv.visibility = View.GONE
                when (s) {
                    MovieSections.NOW_PLAYING.name-> nowPlayingAdapter.setFilm(state.data as ListFilmDTO)
                    MovieSections.POPULAR.name -> popularAdapter.setFilm(state.data as ListFilmDTO)
                    MovieSections.TOP_RATED.name -> topRatedAdapter.setFilm(state.data as ListFilmDTO)
                    MovieSections.UPCOMING.name -> upcomingAdapter.setFilm(state.data as ListFilmDTO)
                }
            }
            is AppState.Error -> {
                binding.progressBarcv.visibility = View.GONE
                binding.mainFragmentRootView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    { viewModel.getFilmsFromServer() })
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
    private fun View.showSnackBar(
        text: String,
        actionText: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeAllListener()
        _binding = null
    }
}