package com.example.foodsaver

import android.content.Context
import com.google.gson.Gson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class JsonDataManager (private val context: Context) {

    fun initDateHolder() {

        val jsonString = context.assets.open("mockData.json").bufferedReader().use { it.readText() }

        val gson = Gson()
        val itemStorage = gson.fromJson(jsonString, Array<Item>::class.java).toList()

        ItemDataHolder.groupOfSameExpireDate = itemStorage!!.sortedBy {
            LocalDate.parse(it.expireDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }.groupBy {
            LocalDate.parse(it.expireDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }

        ItemDataHolder.groupOfSameExpireDate.forEach { (date, items) ->
            val formattedDate = date.toString()
        }
    }

    fun deleteTheItem(position: Int, adapter: ItemAdapter, localDate: LocalDate) {
        ItemDataHolder.deleteItemByIndex(localDate, position)
        println(position)
        adapter.notifyItemRemoved(position)
    }
}