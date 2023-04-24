package com.example.foodsaver

import java.time.LocalDate

object ItemDataHolder {
    var groupOfSameExpireDate: Map<LocalDate, List<Item>> = emptyMap()
}