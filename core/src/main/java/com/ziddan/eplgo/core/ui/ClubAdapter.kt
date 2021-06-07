package com.ziddan.eplgo.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ziddan.eplgo.core.R
import com.ziddan.eplgo.core.databinding.ItemListClubBinding
import com.ziddan.eplgo.core.domain.model.Club
import java.util.ArrayList

class ClubAdapter : RecyclerView.Adapter<ClubAdapter.ListViewHolder>() {

    private var listData = ArrayList<Club>()
    var onItemClick: ((Club) -> Unit)? = null

    fun setData(newListData: List<Club>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_club, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListClubBinding.bind(itemView)
        fun bind(data: Club) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.strTeamBadge)
                    .into(ivItemImage)
                tvItemTitle.text = data.strTeam
                tvItemSubtitle.text = data.strStadiumLocation
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}