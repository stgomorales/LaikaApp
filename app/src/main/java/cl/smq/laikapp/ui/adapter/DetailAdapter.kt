package cl.smq.laikapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.smq.laikapp.R
import cl.smq.laikapp.databinding.BreedDetailRowBinding
import com.bumptech.glide.Glide

class DetailAdapter(private val listener: DetailItemListener, private val context: Context): RecyclerView.Adapter<DetailViewHolder>(){


    interface DetailItemListener {
        fun onClickedItem(url: String)
    }

    private var items = ArrayList<String>()


    fun setItems(items: ArrayList<String>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding: BreedDetailRowBinding = BreedDetailRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) = holder.bind(items[position], context)
}


class DetailViewHolder(private val detailRowBinding: BreedDetailRowBinding, private val listener: DetailAdapter.DetailItemListener): RecyclerView.ViewHolder(detailRowBinding.root),
    View.OnClickListener{

    private lateinit var dogUri: String

    init {
        detailRowBinding.root.setOnClickListener(this)
    }

    fun bind(item: String, context: Context){
        this.dogUri = item

        Glide.with(context)
            .load(dogUri)
            .centerCrop()
            .placeholder(R.drawable.ic_astronaut)
            .thumbnail(0.5f)
            .into(detailRowBinding.breedDetailRowIcon);
    }
    override fun onClick(p0: View?) {
        listener.onClickedItem(dogUri)
    }

}
