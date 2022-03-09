package com.example.currencylist.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencylist.DemoActivity
import com.example.currencylist.R
import com.example.currencylist.adapter.CurrencyListAdapter
import com.example.currencylist.viewModel.CurrencyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyListFragment : Fragment() {

//    companion object {
//        fun newInstance() = CurrencyListFragment()
//    }

    private val viewModel: CurrencyViewModel by activityViewModels()
    private lateinit var currencyListAdapter: CurrencyListAdapter
    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.currency_list_fragment, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loadDataText = requireView().findViewById<TextView>(R.id.tv_loading_data)
        val rvCurrency = requireView().findViewById<RecyclerView>(R.id.rv_currency)

        loadDataText.visibility = View.VISIBLE

        val layoutManager = LinearLayoutManager(this.requireContext())
        rvCurrency.layoutManager = layoutManager
        currencyListAdapter = CurrencyListAdapter(viewModel, activity as DemoActivity)
        rvCurrency.adapter = currencyListAdapter

        viewModel.currencyRecordList.observe(this.viewLifecycleOwner) {
            loadDataText.visibility = View.GONE
            currencyListAdapter.setListRecord(it)
            currencyListAdapter.notifyDataSetChanged()
        }

        //
        viewModel.isAsc.observe(this.viewLifecycleOwner) {
            if(viewModel.isLoadingData.value!!) {
                job?.cancel()
                job = lifecycleScope.launch {
                    sortCurrencyList(it)
                }
            }
        }
    }

    private suspend fun sortCurrencyList(isAsc: Boolean) = withContext(Dispatchers.IO){
        if(isAsc){
            viewModel.getCurrencyDataByAsc()
        }else{
            viewModel.getCurrencyDataByDesc()
        }
    }

}