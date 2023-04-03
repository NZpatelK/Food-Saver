package com.example.foodsaver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView

class MainActivity : AppCompatActivity() {

    private lateinit var listViewAdapter: ExpandableListViewAdapter
    private lateinit var dateList: List<String>
    private lateinit var foodList: HashMap<String, List<String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showList()

        val eListView = findViewById<ExpandableListView>(R.id.eListView)

        listViewAdapter= ExpandableListViewAdapter(this, dateList, foodList)
        eListView.setAdapter(listViewAdapter)


    }

    private fun showList() {
        dateList = ArrayList()
        foodList = HashMap()

        (dateList as ArrayList<String>).add("25/04/2020")
        (dateList as ArrayList<String>).add("26/04/2020")
        (dateList as ArrayList<String>).add("27/04/2020")

        val food1 : MutableList<String> = ArrayList()
        food1.add("food 1")
        food1.add("food 2")
        food1.add("food 3")

        val food2 : MutableList<String> = ArrayList()
        food2.add("food 1")
        food2.add("food 2")
        food2.add("food 3")

        val food3 : MutableList<String> = ArrayList()
        food3.add("food 1")
        food3.add("food 2")
        food3.add("food 3")

        foodList[dateList[0]] = food1
        foodList[dateList[1]] = food2
        foodList[dateList[2]] = food3


    }
}