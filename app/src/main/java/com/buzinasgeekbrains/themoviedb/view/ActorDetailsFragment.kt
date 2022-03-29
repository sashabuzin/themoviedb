package com.buzinasgeekbrains.themoviedb.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.buzinasgeekbrains.themoviedb.viewmodel.ActorDetailsViewModel
import com.buzinasgeekbrains.themoviedb.databinding.ActorDetailsFragmentBinding
import com.buzinasgeekbrains.themoviedb.model.Actor
import com.buzinasgeekbrains.themoviedb.model.ActorDTO
import com.buzinasgeekbrains.themoviedb.model.ActorLoader

class ActorDetailsFragment : Fragment() {

    companion object {
        const val BUNDLE_EXTRA = "actor"
        fun newInstance(bundle: Bundle): ActorDetailsFragment {
            return ActorDetailsFragment().also { it.arguments = bundle }
        }
    }

    private var _binding: ActorDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ActorDetailsViewModel by lazy {
        ViewModelProvider(this).get(ActorDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActorDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filmedInList.layoutManager = LinearLayoutManager(requireActivity())
        arguments?.getParcelable<Actor>(BUNDLE_EXTRA)?.let {

            binding.actorNameMain.text = it.name
            ActorLoader.load(it, object: ActorLoader.OnActorLoadListener {
                override fun onLoaded(actorDTO: ActorDTO) {
                        binding.actorBiographyText.text = actorDTO.biography
                        binding.placeOfBirthList.append(" ${actorDTO.place_of_birth}")
                        binding.birthdayList.append(" ${actorDTO.birthday}")
                        binding.genderList.append(" ${if (actorDTO.gender == 1) " Male" else " Female"}")
                        binding.popularityList.append(" ${actorDTO.popularity.toString()}")

                }

                override fun onFailed(throwable: Throwable) {
                    Toast.makeText(requireActivity(), throwable.message, Toast.LENGTH_SHORT).show()
                }
            })

        }
    }

//    private fun displayActorInfo(actorDTO: ActorDTO) {
//
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}