package com.buzinasgeekbrains.themoviedb.view

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.buzinasgeekbrains.themoviedb.viewmodel.AppState
import com.buzinasgeekbrains.themoviedb.viewmodel.FilmDetailsViewModel
import com.buzinasgeekbrains.themoviedb.databinding.FilmDetailsFragmentBinding
import com.buzinasgeekbrains.themoviedb.model.Film
import com.buzinasgeekbrains.themoviedb.model.FilmDTO
import com.buzinasgeekbrains.themoviedb.model.FilmLoaderService
import com.buzinasgeekbrains.themoviedb.model.MOVIE_ID

const val DETAILS_INTENT_FILTER = "DETAILS INTENT FILTER"

class FilmDetailsFragment : Fragment() {

    private val callBackReceiver = FilmDetailsViewModel.MyReceiver()

    companion object {
        const val BUNDLE_EXTRA = "film"
        fun newInstance(film: Bundle): FilmDetailsFragment {
            val fragment = FilmDetailsFragment()
            fragment.arguments = film
            return fragment
        }
    }

    private var _binding: FilmDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FilmDetailsViewModel
    private var filmId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it)
                .registerReceiver(callBackReceiver, IntentFilter(DETAILS_INTENT_FILTER))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FilmDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FilmDetailsViewModel::class.java)
        arguments?.getParcelable<FilmDTO>(BUNDLE_EXTRA)?.let {
            filmId = it.id ?: 0
        }
            getFilmFromService(filmId)
            viewModel.also {
//                it.getFilmFromServer(filmId)
                it.getData().observe(viewLifecycleOwner, Observer { appState ->
                    render(appState)
                })
            }

    }

    private fun getFilmFromService(id: Int) {
        requireActivity().startService(Intent(requireActivity(), FilmLoaderService::class.java).apply {
            putExtra(MOVIE_ID, id)
        })
    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success<*> -> {
                val film = state.data as FilmDTO
                binding.progressBarcv.visibility = View.GONE
                binding.filmNameMain.text = film.title
                binding.ratingList.append(film.vote_average.toString())
                binding.budgetList.append(film.budget.toString())
                binding.revenueList.append(film.revenue.toString())
                binding.releaseDateList.append(film.release_date)
                binding.filmOverview.text = film.overview
            }
            is AppState.Error -> {
                getFilmFromService(filmId)
            }
            is AppState.Loading -> {
                binding.progressBarcv.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(callBackReceiver)
        }

        super.onDestroy()
        _binding = null

    }

}