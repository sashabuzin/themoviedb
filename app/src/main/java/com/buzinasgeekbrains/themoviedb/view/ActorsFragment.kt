package com.buzinasgeekbrains.themoviedb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.buzinasgeekbrains.themoviedb.R
import com.buzinasgeekbrains.themoviedb.databinding.ActorsFragmentBinding
import com.buzinasgeekbrains.themoviedb.model.PopularActorsListDTO
import com.buzinasgeekbrains.themoviedb.viewmodel.ActorsViewModel
import com.buzinasgeekbrains.themoviedb.viewmodel.AppState
import com.google.android.material.snackbar.Snackbar


class ActorsFragment : Fragment() {

    companion object {
        fun newInstance() = ActorsFragment()
    }

    private var _binding: ActorsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ActorsViewModel by lazy {
        ViewModelProvider(this).get(ActorsViewModel::class.java)
    }

    private val adapter = ActorsFragmentAdapter { actorDTO ->
        activity?.supportFragmentManager?.apply {
            beginTransaction()
                .add(R.id.container_main, ActorDetailsFragment.newInstance(Bundle().apply {
                    putParcelable(ActorDetailsFragment.BUNDLE_EXTRA, actorDTO) }))
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActorsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            binding.actorsRecyclerView.also {
                it.adapter = adapter
                it.layoutManager = GridLayoutManager(requireActivity(), 2)
            }
        viewModel.also{
            it.getPopularActorsFromServer()
            it.getData().observe(viewLifecycleOwner, Observer {appState ->

                render(appState)
            })

        }
    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success<*> -> {
                adapter.setActor(state.data as PopularActorsListDTO)

                binding.progressBarcv.visibility = View.GONE
            }
            is AppState.Error -> {
                binding.progressBarcv.visibility = View.GONE
                binding.actorsFragmentRootView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    { viewModel.getPopularActorsFromServer()})
            }
            is AppState.Loading -> {
                binding.progressBarcv.visibility = View.VISIBLE
            }
        }
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
        adapter.removeListener()
        _binding = null
    }
}