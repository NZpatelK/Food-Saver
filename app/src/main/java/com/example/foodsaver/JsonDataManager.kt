package com.example.foodsaver

import android.content.Context
import com.google.gson.Gson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * This class is to setup the access the data from the local json file to fetch the data
 * and save into data holder.
 */
class JsonDataManager (private val context: Context) {

    /**
     * This init function to get data from the local json file and add into the database.
     */
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

    //This is delete function to delete the item from the list.
    fun deleteTheItem(position: Int, adapter: ItemAdapter, localDate: LocalDate) {
        ItemDataHolder.deleteItemByIndex(localDate, position)
        println(position)
        adapter.notifyItemRemoved(position)
    }
}