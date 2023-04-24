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
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [PendingExpireFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PendingExpireFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pending_expire, container, false)

        //Floating Action Button onClick function.
        val fab = view.findViewById<ExtendedFloatingActionButton>(R.id.floating_action)

        fab.setOnClickListener {
            val intent = Intent(requireContext(), AddItemActivity::class.java)
            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(),  fab,  fab.transitionName )
            startActivity(intent, option.toBundle())
        }

        val jsonDataManager = JsonDataManager(requireContext())
        jsonDataManager.initDateHolder()

        //This is output the display of list expire date with total items.

        val prodRecyclerView = view.findViewById<RecyclerView>(R.id.PendingExpireTotalItemRecyclerView)
        prodRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = PendingExpireTotalItemsAdapter(ItemDataHolder.groupOfSameExpireDate)
        }


        //Floating action button
        //when user scroll down then button will shrink.
        //Scroll up then button will extend.
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

}