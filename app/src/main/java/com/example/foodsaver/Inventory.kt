package com.example.foodsaver

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * This class is meant for inventory management of short-life food stock to ensure that it does not go to waste when it expires.
 * Its purpose is to save you from waste. You will be able to view the same product but with different expiry dates and help you select the right product that is closest to the expiry date and use it first before the next one.
 * I haven't created an inventory in this class, but in the future, I plan to come back and add a new feature.
 */
class Inventory : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

    companion object {
    }
}