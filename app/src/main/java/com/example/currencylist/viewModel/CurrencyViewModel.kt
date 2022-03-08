package com.example.currencylist.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencylist.db.CurrencyEntity
import com.example.currencylist.db.CurrencyRepositoryImp
import com.example.currencylist.db.DatabaseRepository
import com.example.currencylist.model.CurrencyModel

class CurrencyViewModel (private val roomDatabaseRepository: DatabaseRepository) : ViewModel() {

    val currencyRecordList: MutableLiveData<List<CurrencyModel>> = MutableLiveData()
    val isLoadingData: MutableLiveData<Boolean> = MutableLiveData()
    val isAsc: MutableLiveData<Boolean> = MutableLiveData()

    init{
        isAsc.value = true
        isLoadingData.value = false
    }

    fun getCurrencyDataByAsc(){
        roomDatabaseRepository.getCurrencyByAsc(object: CurrencyRepositoryImp.ItemCallback {
            override fun onItemsResult(itemModels: List<CurrencyModel>) {
                currencyRecordList.postValue(itemModels)
            }
        })
    }

    fun getCurrencyDataByDesc(){
        roomDatabaseRepository.getCurrencyByDesc(object: CurrencyRepositoryImp.ItemCallback {
            override fun onItemsResult(itemModels: List<CurrencyModel>) {
                currencyRecordList.postValue(itemModels)
            }
        })
    }

    fun insertCurrency(currencyList: List<CurrencyModel>){
        currencyList.forEach {
            roomDatabaseRepository.insertCurrency(getCurrencyDetails(
                name = it.name,
                symbol = it.symbol
            ))
        }
    }

    private fun getCurrencyDetails(name: String, symbol: String): CurrencyEntity {
        val currencyEntity = CurrencyEntity()

        currencyEntity.name = name
        currencyEntity.symbol = symbol

        return currencyEntity
    }

    fun isDbEmpty(): Boolean {
        return roomDatabaseRepository.isDbEmpty()
    }

    fun sorting() {
        isAsc.postValue(!isAsc.value!!)
    }

    fun loadingData() {
        isLoadingData.postValue(true)
    }
}