package com.yoghi.moviecatalog.ui.home.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yoghi.moviecatalog.R
import com.yoghi.moviecatalog.data.DataModel
import com.yoghi.moviecatalog.utils.Helper.API_IMAGE_ENDPOINT
import com.yoghi.moviecatalog.utils.Helper.ENDPOINT_POSTER_SIZE_W185
import com.yoghi.moviecatalog.utils.Helper.setImageWithGlide
import kotlinx.android.synthetic.main.item_row_data.view.*

class DataAdapter(private val callback: DataCallback) :
    RecyclerView.Adapter<DataAdapter.ListViewHolder>() {
    private val listData = ArrayList<DataModel>()

    fun setData(data: List<DataModel>) {
        if (data.isNullOrEmpty()) return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: DataModel) {
            with(itemView) {
                data.poster?.let {
                    setImageWithGlide(context, API_IMAGE_ENDPOINT+ ENDPOINT_POSTER_SIZE_W185 + it, img_data)
                }
                tv_data_title.text = data.name
                tv_data_desc.text = data.desc

                card_item.setOnClickListener {
                    callback.onItemClicked(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_data, parent, false)
        )

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: DataAdapter.ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }
}