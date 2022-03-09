package com.example.currencylist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.currencylist.Listener.CurrencyItemClickListener
import com.example.currencylist.data.CurrencyData
import com.example.currencylist.db.DatabaseRepository
import com.example.currencylist.db.RoomDbHelper
import com.example.currencylist.factory.CurrencyViewModelFactory
import com.example.currencylist.model.CurrencyModel
import com.example.currencylist.view.CurrencyListFragment
import com.example.currencylist.viewModel.CurrencyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class DemoActivity : AppCompatActivity(R.layout.activity_main), CurrencyItemClickListener {

    private lateinit var viewModel: CurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<CurrencyListFragment>(R.id.fragment_container_view)
            }

            viewModel = ViewModelProvider(this, CurrencyViewModelFactory(DatabaseRepository(RoomDbHelper(context = applicationContext)))).get(CurrencyViewModel::class.java)

            val loadBtn = findViewById<AppCompatButton>(R.id.btn_load)
            loadBtn.setOnClickListener {
                runBlocking {
                    insertCurrency(CurrencyData.getInitCurrencyData())
                }
            }

            val sortBtn = findViewById<AppCompatButton>(R.id.btn_sort)
            sortBtn.setOnClickListener {
                sortCurrency()
            }
        }
    }

    private suspend fun insertCurrency(currencyList: List<CurrencyModel>) = withContext(Dispatchers.IO){
        if(viewModel.isDbEmpty()){
            viewModel.insertCurrency(currencyList)
        }
        viewModel.getCurrencyDataByAsc()

        // update the status of data loaded
        viewModel.loadedData()
    }

    private fun sortCurrency() {
        // check the currency is loaded
        if(viewModel.isLoadedData.value!!){
            viewModel.sorting()
        }else{
            Toast.makeText(this, "Please load data first!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(name: String) {
        Toast.makeText(this, "you are clicked $name", Toast.LENGTH_SHORT).show()
    }

}