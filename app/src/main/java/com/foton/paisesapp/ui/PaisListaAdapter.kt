package com.foton.paisesapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.foton.paisesapp.databinding.ItemPaisBinding
import com.foton.paisesdadosapp.model.Pais

class PaisListaAdapter : ListAdapter<Pais, PaisListaAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPaisBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemPaisBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Pais) {
            binding.tvNomePais.text = item.nomePais
            binding.tvAlpha2Code.text = item.codigoPais
        }
    }
}


class DiffCallback : DiffUtil.ItemCallback<Pais>() {
    override fun areItemsTheSame(oldItem: Pais, newItem: Pais) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Pais, newItem: Pais) = oldItem.nomePais == newItem.nomePais
}
