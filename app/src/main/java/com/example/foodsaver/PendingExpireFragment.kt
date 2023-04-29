package com.example.foodsaver

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton



/**
 * This page shows a list of expiry dates along with the total number of items expiring on each date.
 */
class PendingExpireFragment : Fragment() {

    private lateinit var adapter: PendingExpireTotalItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pending_expire, container, false)

        val fab = view.findViewById<ExtendedFloatingActionButton>(R.id.floating_action)

        // The click listener for this floating action button will navigate
        // the user to the page for adding a new item when the button is clicked.
        fab.setOnClickListener {
            val intent = Intent(requireContext(), AddItemActivity::class.java)
            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(),  fab,  fab.transitionName )
            startActivity(intent, option.toBundle())
        }

        val jsonDataManager = JsonDataManager(requireContext())
        jsonDataManager.initDateHolder()

        //This is the output displaying the list of expiration dates along with the total number of items.
        val prodRecyclerView = view.findViewById<RecyclerView>(R.id.PendingExpireTotalItemRecyclerView)
        prodRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = PendingExpireTotalItemsAdapter(ItemDataHolder.groupOfSameExpireDate)
            this@PendingExpireFragment.adapter = adapter as PendingExpireTotalItemsAdapter

        }


        //Floating action button:
        // When the user scrolls down, the button will shrink,
        // and when they scroll up, it will extend
        prodRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Scroll down
                if (dy > 20 && fab.isExtended) {
                    fab.shrink()
                    fab.visibility = View.VISIBLE
                }

                // Scroll up
                if (dy < -20 && !fab.isExtended) {
                    fab.extend()
                    fab.visibility = View.VISIBLE
                }

                // At the top
                if (!recyclerView.canScrollVertically(-1)) {
                    fab.extend()
                    fab.visibility = View.VISIBLE
                }

                // At the top
                if (!recyclerView.canScrollVertically(1)) {
                    fab.visibility = View.GONE
                }
            }
        })

        return view
    }

    //This  function is to update the display with the latest data.
    override fun onResume() {
        super.onResume()
        adapter.setPendingExpireTotalItems(ItemDataHolder.groupOfSameExpireDate)
    }

}