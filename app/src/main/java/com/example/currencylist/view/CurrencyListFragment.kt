package com.example.currencylist.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencylist.Listener.CurrencyItemClickListener
import com.example.currencylist.R
import com.example.currencylist.adapter.CurrencyListAdapter
import com.example.currencylist.viewModel.CurrencyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class CurrencyListFragment : Fragment(), CurrencyItemClickListener {

//    companion object {
//        fun newInstance() = CurrencyListFragment()
//    }

    private val viewModel: CurrencyViewModel by activityViewModels()
    private lateinit var currencyListAdapter: CurrencyListAdapter

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

        //viewModel = ViewModelProvider(this, CurrencyViewModelFactory(DatabaseRepository(RoomDbHelper(this.requireContext())))).get(CurrencyViewModel::class.java)

//        viewModel =
//            ViewModelProvider(
//                this,
//                CurrencyViewModelFactory(DatabaseRepository(RoomDbHelper(this.requireContext())))
//            ).get(
//                CurrencyViewModel::class.java
//            )

        loadDataText.visibility = View.VISIBLE

        val layoutManager = LinearLayoutManager(this.requireContext())
        rvCurrency.layoutManager = layoutManager
        currencyListAdapter = CurrencyListAdapter(viewModel, this)
        rvCurrency.adapter = currencyListAdapter

        viewModel.currencyRecordList.observe(this.viewLifecycleOwner) {
            loadDataText.visibility = View.GONE
            currencyListAdapter.setListRecord(it)
            currencyListAdapter.notifyDataSetChanged()
        }

        viewModel.isAsc.observe(this.viewLifecycleOwner) {
            Log.i("isAsc", it.toString())
            if(viewModel.isLoadingData.value!!) {
                runBlocking {
                    sortCurrencyList(it)
                }
            }
        }
//        runBlocking {
//            getCurrencyList()
//        }

    }

    private suspend fun sortCurrencyList(isAsc: Boolean) = withContext(Dispatchers.IO){
        if(isAsc){
            viewModel.getCurrencyDataByAsc()
        }else{
            viewModel.getCurrencyDataByDesc()
        }
    }

//    private suspend fun getCurrencyList() = withContext(Dispatchers.IO){
//        viewModel.getCurrencyData()
//    }

    override fun onClick(name: String) {
        Toast.makeText(this.requireContext(), "you are clicked $name", Toast.LENGTH_SHORT).show()
    }

}