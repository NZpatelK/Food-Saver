package com.example.foodsaver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ListOfItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_items)

        val itemName = findViewById<TextView>(R.id.itemName)
        val totalItems = findViewById<TextView>(R.id.totalStock)

        val ListOfProducts = intent.getSerializableExtra("productsList") as List<Item>

        itemName.text = ListOfProducts?.get(1)?.productName


    }
}