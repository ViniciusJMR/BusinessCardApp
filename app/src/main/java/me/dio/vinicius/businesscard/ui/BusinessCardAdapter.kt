package me.dio.vinicius.businesscard.ui

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.dio.vinicius.businesscard.databinding.ItemBusinessCardBinding
import me.dio.vinicius.businesscard.domain.BusinessCard

class BusinessCardAdapter(
) :
    ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerEdit: (BusinessCard) -> Unit = {}
    var listenerDelete: (BusinessCard) -> Unit = {}
    var listenerShare: (View) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemBusinessCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BusinessCard) {
            binding.cardViewHolder = this
            binding.card = item
            if (item.backgroundColor != "")
                binding.mcvCard.setCardBackgroundColor(Color.parseColor(item.backgroundColor))
            else
                binding.mcvCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        }

        fun deleteCard(v: View){
            listenerDelete(getItem(adapterPosition))
            notifyItemRemoved(adapterPosition)
        }

        fun editCard(v: View){
            listenerEdit(getItem(adapterPosition))
            notifyItemChanged(adapterPosition)
        }

        fun shareCard(v: View){
            listenerShare(v)
        }
    }

}

class DiffCallback : DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
        oldItem.id == newItem.id
}