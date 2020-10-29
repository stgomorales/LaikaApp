package cl.smq.laikapp.ui.dogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cl.smq.laikapp.R
import cl.smq.laikapp.data.entities.DogBreed
import cl.smq.laikapp.databinding.BreedListFragmentBinding
import cl.smq.laikapp.ui.adapter.BreedAdapter
import cl.smq.laikapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedListFragment : Fragment(), BreedAdapter.BreedItemListener {

    private lateinit var binding: BreedListFragmentBinding
    private val viewModel: BreedListViewModel by viewModels()
    private lateinit var adapter: BreedAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = BreedListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView(){
        adapter = BreedAdapter(this)
        binding.listFragmetRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.listFragmetRecycler.adapter = adapter
        binding.listFragmetRecycler.addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
    }

    private fun setupObservers(){
        viewModel.dogBreeds.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.onSuccess ->{
                    if (!it.data.isNullOrEmpty()){
                        adapter.setItems(it.data[0].breeds as ArrayList<String>)
                        binding.listFragmentProgress.visibility = View.GONE
                    }
                }
                Resource.Status.onError ->{
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.onLoading ->{
                    binding.listFragmentProgress.visibility = View.GONE
                }
            }
        })
    }

    override fun onClickedItem(breed: String) {
        findNavController().navigate(
            R.id.action_breedListFragment_to_dogDetailFragment,
            bundleOf("breed" to breed)
        )
    }
}