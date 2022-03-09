package com.example.currencylist.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencylist.model.CurrencyModel

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: CurrencyEntity): Long

    @Query("SELECT id, name, symbol FROM currency order by name asc")
    fun getAll(): List<CurrencyModel>

    @Query("SELECT id, name, symbol FROM currency order by name desc")
    fun getAllDesc(): List<CurrencyModel>

    @Query("SELECT COUNT(*) FROM currency")
    fun checkEmpty(): Int

}