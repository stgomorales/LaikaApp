package cl.smq.laikapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import cl.smq.laikapp.databinding.DogDetailFragmentBinding
import cl.smq.laikapp.ui.adapter.DetailAdapter
import cl.smq.laikapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogDetailFragment : Fragment(), DetailAdapter.DetailItemListener {

    private lateinit var binding: DogDetailFragmentBinding
    private val viewModel: DogDetailViewModel by viewModels()
    private lateinit var adapter: DetailAdapter


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
        val numberOfColumns = 3
        adapter = DetailAdapter(this, this.requireContext())
        binding.detailRecycler.layoutManager = GridLayoutManager(this.requireContext(), numberOfColumns)
        binding.detailRecycler.adapter = adapter
    }

    private fun setupObservers(){
        viewModel.dogDetail.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.onSuccess ->{
                    if (it.data != null){
                        binding.detailProgress.visibility = View.GONE
                        adapter.setItems(it.data?.dogImages as ArrayList<String>)
                    }
                }
                Resource.Status.onError ->{
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.onLoading ->{
                    binding.detailProgress.visibility = View.VISIBLE
                }
            }
        })
    }


    override fun onClickedItem(url: String) {
        TODO("Not yet implemented")
    }
}