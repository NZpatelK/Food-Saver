package com.example.foodsaver

import java.time.LocalDate

/**
 * This is data holder object is to storage the data in the data holder.
 * It is like database and this is temporary location to storage. In the future will move to cloud database.
 */
object ItemDataHolder {
    var groupOfSameExpireDate: Map<LocalDate, List<Item>> = emptyMap()

    //This is delete function to delete the specific item from the list.
    fun deleteItemByIndex(date: LocalDate, index: Int) {
        val itemsOnDate = groupOfSameExpireDate[date]?.toMutableList() ?: return // Get the list of items for the given date, if it exists
        if (index in itemsOnDate.indices) {
            itemsOnDate.removeAt(index) // Remove the item at the given index from the list
            if (itemsOnDate.isEmpty()) {
                groupOfSameExpireDate = groupOfSameExpireDate - date // If the list is now empty, remove the date from the map
            } else {
                groupOfSameExpireDate = groupOfSameExpireDate + (date to itemsOnDate) // Update the map with the new list of items for the date
            }
        }
    }

    //This is insert item to add a new item into the list.
    fun insertItem(date: LocalDate, item: Item) {
        val itemsOnDate = groupOfSameExpireDate[date]?.toMutableList() ?: mutableListOf() // Get the list of items for the given date, or create a new empty list if it doesn't exist
        itemsOnDate.add(item) // Add the new item to the list
        groupOfSameExpireDate = groupOfSameExpireDate + (date to itemsOnDate) // Update the map with the new list of items for the date
        groupOfSameExpireDate = groupOfSameExpireDate.toSortedMap(Comparator.naturalOrder())

    }

}