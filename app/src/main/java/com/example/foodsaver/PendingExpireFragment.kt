package com.example.foodsaver

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


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
        val fab = view.findViewById<ExtendedFloatingActionButton>(R.id.floating_action)

        fab.setOnClickListener {
            val intent = Intent(requireContext(), AddItemActivity::class.java)
            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(),  fab,  fab.transitionName )
            startActivity(intent, option.toBundle())
        }
        return view
    }

}