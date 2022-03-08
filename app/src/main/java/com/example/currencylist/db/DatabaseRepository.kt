package com.example.currencylist.db

import com.example.currencylist.model.CurrencyModel

interface DatabaseRepositoryImp {
    fun insertCurrency(currencyDetail: CurrencyEntity)
}

interface CurrencyRepositoryImp {
    interface ItemCallback {
        fun onItemsResult(itemModels: List<CurrencyModel>)
    }
}

class DatabaseRepository constructor(private var database: RoomDbHelper) :
    DatabaseRepositoryImp{

    override fun insertCurrency(currencyDetail: CurrencyEntity) {
        database.getCurrencyDao().insert(currencyDetail)
    }

    fun getCurrencyByAsc(itemCallback: CurrencyRepositoryImp.ItemCallback) {
        val list = mutableListOf<CurrencyModel>()

        database.getCurrencyDao().getAll().forEach {
            val currencyItem = CurrencyModel(
                id = it.id,
                name = it.name,
                symbol = it.symbol
            )
            list.add(currencyItem)
        }

        itemCallback.onItemsResult(list)
    }

    fun getCurrencyByDesc(itemCallback: CurrencyRepositoryImp.ItemCallback) {
        val list = mutableListOf<CurrencyModel>()

        database.getCurrencyDao().getAllDesc().forEach {
            val currencyItem = CurrencyModel(
                id = it.id,
                name = it.name,
                symbol = it.symbol
            )
            list.add(currencyItem)
        }

        itemCallback.onItemsResult(list)
    }

    fun isDbEmpty(): Boolean {
        return database.getCurrencyDao().checkEmpty() == 0
    }

}