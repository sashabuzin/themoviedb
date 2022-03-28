package com.buzinasgeekbrains.themoviedb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buzinasgeekbrains.themoviedb.R
import com.buzinasgeekbrains.themoviedb.databinding.ActorsFragmentBinding
import com.buzinasgeekbrains.themoviedb.databinding.MainFragmentBinding
import com.buzinasgeekbrains.themoviedb.model.Actor
import com.buzinasgeekbrains.themoviedb.viewmodel.ActorsViewModel
import com.buzinasgeekbrains.themoviedb.viewmodel.AppState
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.actors_fragment.*
import kotlinx.android.synthetic.main.main_fragment.*

class ActorsFragment : Fragment() {

    companion object {
        fun newInstance() = ActorsFragment()
    }

    private var _binding: ActorsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ActorsViewModel by lazy {
        ViewModelProvider(this).get(ActorsViewModel::class.java)
    }

    private val adapter = ActorsFragmentAdapter { actor ->
        activity?.supportFragmentManager?.apply {
            beginTransaction()
                .add(R.id.container_main, ActorDetailsFragment.newInstance(Bundle().apply {
                    putParcelable(ActorDetailsFragment.BUNDLE_EXTRA, actor) }))
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
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            render(it)
        })
        viewModel.getActorFromLocalStorage()
    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success<*> -> {
                binding.progressBarcv.visibility = View.GONE
                adapter.setActor(state.data as List<*>)
            }
            is AppState.Error -> {
                binding.progressBarcv.visibility = View.GONE
                actorsFragmentRootView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    { viewModel.getActorFromLocalStorage() })
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