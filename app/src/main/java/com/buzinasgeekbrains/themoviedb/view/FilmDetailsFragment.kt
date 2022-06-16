package com.buzinasgeekbrains.themoviedb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.buzinasgeekbrains.themoviedb.viewmodel.AppState
import com.buzinasgeekbrains.themoviedb.viewmodel.FilmDetailsViewModel
import com.buzinasgeekbrains.themoviedb.databinding.FilmDetailsFragmentBinding
import com.buzinasgeekbrains.themoviedb.model.Film
import com.buzinasgeekbrains.themoviedb.model.FilmDTO

class FilmDetailsFragment : Fragment() {

    companion object {

        const val BUNDLE_EXTRA = "film"
        fun newInstance(bundle: Bundle): FilmDetailsFragment {
            val fragment = FilmDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: FilmDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FilmDetailsViewModel

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
            binding.progressBarcv.visibility = View.GONE
            binding.filmNameMain.text = it.title
            binding.ratingList.append(it.vote_average.toString())
            binding.budgetList.append(it.budget.toString())
            binding.revenueList.append(it.revenue.toString())
            binding.releaseDateList.append(it.release_date)
            binding.filmOverview.text = it.overview
        }
    }

//    private fun render(state: AppState) {
//        when (state) {
//            is AppState.Success<*> -> {
//
//                val film = state.data as Film
//                binding.progressBarcv.visibility = View.GONE
//                binding.filmNameMain.text = film.name
//                binding.ratingList.append(film.rating.toString())
//                binding.budgetList.append(film.budget.toString())
//                binding.revenueList.append(film.revenue.toString())
//                binding.releaseDateList.append(film.releaseDate)
//                binding.filmOverview.text = film.overview
//
//            }
//            is AppState.Error -> {
//                viewModel.getFilmFromLocalStorage()
//            }
//            is AppState.Loading -> {
//                binding.progressBarcv.visibility = View.VISIBLE
//            }
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}