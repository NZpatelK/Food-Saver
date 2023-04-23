package com.example.foodsaver

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class PendingExpireTotalItemsAdapter (private val pendingExpireTotalItemsList: ArrayList<PendingExpireTotalItems>) : RecyclerView.Adapter<PendingExpireTotalItemsAdapter.PendingExpireTotalItemsHolder> () {
    class PendingExpireTotalItemsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val outputDate: TextView = view.findViewById(R.id.outputDate)
        val totalItems: TextView = view.findViewById((R.id.totalItems))
        val cardView: CardView = view.findViewById(R.id.pendingExpireItemsCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingExpireTotalItemsHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pending_expire_total_items_card, parent, false)

        return  PendingExpireTotalItemsHolder(view)

    }

    override fun getItemCount(): Int = pendingExpireTotalItemsList.size

    override fun onBindViewHolder(holder: PendingExpireTotalItemsHolder, position: Int) {
        holder.outputDate.text = pendingExpireTotalItemsList[position].date
        holder.totalItems.text = pendingExpireTotalItemsList[position].totalItems.toString()

        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale_up))

        holder.cardView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "numer: $position", Toast.LENGTH_SHORT).show()

            val intent = Intent(holder.itemView.context, ListOfItemsActivity::class.java)
            intent.putExtra("productTitle", pendingExpireTotalItemsList[position].date)
            intent.putExtra("productNum", pendingExpireTotalItemsList[position].totalItems.toString())

            holder.itemView.context.startActivity(intent)
        }

    }
}