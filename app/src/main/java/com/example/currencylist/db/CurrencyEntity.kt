package com.example.currencylist.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
class CurrencyEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @NonNull
    var name: String = ""

    @NonNull
    var symbol: String = ""
}