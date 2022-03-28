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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*


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
    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

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
        activity?.supportFragmentManager?.apply {
            beginTransaction()
                .add(R.id.container_main, FilmDetailsFragment.newInstance(Bundle().apply {
                    putParcelable(FilmDetailsFragment.BUNDLE_EXTRA, film)
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
                    NOW_PLAYING -> nowPlayingAdapter.setFilm(state.data as List<*>)
                    POPULAR -> popularAdapter.setFilm(state.data as List<*>)
                    TOP_RATED -> topRatedAdapter.setFilm(state.data as List<*>)
                    UPCOMING -> upcomingAdapter.setFilm(state.data as List<*>)
                }
            }
            is AppState.Error -> {
                binding.progressBarcv.visibility = View.GONE
                mainFragmentRootView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    { viewModel.getFilmsFromLocalStorage() })
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