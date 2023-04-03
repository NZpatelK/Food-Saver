package com.example.foodsaver

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class ExpandableListViewAdapter internal constructor(private val context: Context, private val dateList: List<String>, private val foodList: HashMap<String, List<String>>):
    BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return dateList.size

    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.foodList[this.dateList[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return dateList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.foodList[this.dateList[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val dateHeader = getGroup(groupPosition) as String

        if (convertView == null){

            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.date_list, null)
        }

        val dateItem = convertView!!.findViewById<TextView>(R.id.date)
        dateItem.setText(dateHeader)

        return convertView

    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val foodHeader = getChild(groupPosition, childPosition) as String

        if (convertView == null){

            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.food_list, null)
        }

        val fooditem = convertView!!.findViewById<TextView>(R.id.FoodItem)
        fooditem.setText(foodHeader)

        return convertView
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}