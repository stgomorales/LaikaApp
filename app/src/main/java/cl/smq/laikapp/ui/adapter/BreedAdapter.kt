package cl.smq.laikapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.smq.laikapp.data.entities.DogBreed
import cl.smq.laikapp.databinding.BreedRowBinding

class BreedAdapter (private val listener: BreedItemListener): RecyclerView.Adapter<BreedViewHolder>(){

    interface BreedItemListener {
        fun onClickedItem(breed: String)
    }

    private var items = ArrayList<String>()

    fun setItems(items: ArrayList<String>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val binding: BreedRowBinding = BreedRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) = holder.bind(items[position])


    override fun getItemCount(): Int = items.size

}

class BreedViewHolder(private val breedRowBinding: BreedRowBinding, private  val listener: BreedAdapter.BreedItemListener): RecyclerView.ViewHolder(breedRowBinding.root),
        View.OnClickListener{

    private lateinit var breed: String

    init {
        breedRowBinding.root.setOnClickListener(this)
    }

    fun bind(item: String){
        this.breed = item
        breedRowBinding.bredRowName.text = item

    }
    override fun onClick(p0: View?) {
        listener.onClickedItem(breed)
    }

}
