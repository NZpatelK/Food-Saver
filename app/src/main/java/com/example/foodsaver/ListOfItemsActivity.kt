package com.example.foodsaver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListOfItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_items)

        val ListOfProducts = intent.getSerializableExtra("productsList") as List<Item>

        if (ListOfProducts != null) {
            val prodRecyclerView = findViewById<RecyclerView>(R.id.itemRecyclerView)
            prodRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = ItemAdapter(ListOfProducts)
            }
        } else {
            // Handle the case when name is null or not of type List<Items>
        }


    }
}