package com.example.foodsaver

import java.time.LocalDate

/**
 * This data holder object is used to store data temporarily. It functions like a database, serving as
 * a temporary location for data storage. In the future, the data will be moved to a cloud database.
 */
object ItemDataHolder {
    var groupOfSameExpireDate: Map<LocalDate, List<Item>> = emptyMap()

    //This is the delete function used to remove a specific item from a list.
    fun deleteItemByIndex(date: LocalDate, index: Int) {
        val itemsOnDate = groupOfSameExpireDate[date]?.toMutableList() ?: return // Get the list of items for the given date, if it exists
        if (index in itemsOnDate.indices) {
            itemsOnDate.removeAt(index) // Remove the item at the given index from the list
            groupOfSameExpireDate = if (itemsOnDate.isEmpty()) {
                groupOfSameExpireDate - date // If the list is now empty, remove the date from the map
            } else {
                groupOfSameExpireDate + (date to itemsOnDate) // Update the map with the new list of items for the date
            }
        }
    }

    //This is the "insert item" function used to add a new item to a list.
    fun insertItem(date: LocalDate, item: Item) {
        val itemsOnDate = groupOfSameExpireDate[date]?.toMutableList() ?: mutableListOf() // Get the list of items for the given date, or create a new empty list if it doesn't exist
        itemsOnDate.add(item) // Add the new item to the list
        groupOfSameExpireDate = groupOfSameExpireDate + (date to itemsOnDate) // Update the map with the new list of items for the date
        groupOfSameExpireDate = groupOfSameExpireDate.toSortedMap(Comparator.naturalOrder())

    }

}