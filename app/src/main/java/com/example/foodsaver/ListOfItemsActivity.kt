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

        val name = intent.getStringExtra("productTitle")
        val total = intent.getStringExtra("productNum")

        itemName.text = name
        totalItems.text = total
    }
}