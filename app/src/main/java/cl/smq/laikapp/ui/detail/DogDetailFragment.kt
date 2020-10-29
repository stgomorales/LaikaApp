package cl.smq.laikapp.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import cl.smq.laikapp.R
import cl.smq.laikapp.databinding.DogDetailFragmentBinding

class DogDetailFragment : Fragment() {

    private lateinit var binding: DogDetailFragmentBinding
    private val viewModel: DogDetailViewModel by viewModels()


    companion object {
        fun newInstance() = DogDetailFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DogDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("breed")?.let { viewModel.star(it) }
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView(){

    }

    private fun setupObservers(){

    }
}