package com.example.currencylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencylist.Listener.CurrencyItemClickListener
import com.example.currencylist.databinding.CellCurrencyItemBinding
import com.example.currencylist.model.CurrencyModel
import com.example.currencylist.viewModel.CurrencyViewModel

class CurrencyListAdapter(private val viewModel: CurrencyViewModel, private val listener: CurrencyItemClickListener) : RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>() {

    private var list: List<CurrencyModel>? = viewModel.currencyRecordList.value

    fun setListRecord(listValue: List<CurrencyModel>){
        list = listValue
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list!![position]
        holder.bind(viewModel, item)
        holder.itemView.setOnClickListener {
            listener.onClick(item.name)
        }
    }

    override fun getItemCount(): Int {
        return list?.count() ?: 0
    }

    class ViewHolder private constructor(private val binding: CellCurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: CurrencyViewModel, item: CurrencyModel) {

            binding.currencyViewModel = viewModel
            binding.currencyModel = item

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CellCurrencyItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}