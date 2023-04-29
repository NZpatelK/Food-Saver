package com.example.foodsaver

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Adapter class for controlling the view of a list of pending
 * expiry dates with the total number of items expiring on the same date page.
 */
class PendingExpireTotalItemsAdapter(private var pendingExpireTotalItemsList: Map<LocalDate, List<Item>>) : RecyclerView.Adapter<PendingExpireTotalItemsAdapter.PendingExpireTotalItemsHolder> () {
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

    @SuppressLint("NotifyDataSetChanged")
    fun setPendingExpireTotalItems(newList: Map<LocalDate, List<Item>>) {
        pendingExpireTotalItemsList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = pendingExpireTotalItemsList.size

    override fun onBindViewHolder(holder: PendingExpireTotalItemsHolder, position: Int) {

        val key = pendingExpireTotalItemsList.keys.toTypedArray()[position]
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        holder.outputDate.text = key.format(formatter)
        holder.totalItems.text = pendingExpireTotalItemsList[key]?.size.toString()

        //This shows the color of the card to alert the user that the items will expire very soon.
        // This code is temporary and will be changed with the actual date. Right now,
        // it uses an array index for demonstration purposes only.
        when (position) {
            0 -> {
                holder.cardView.setBackgroundResource(R.drawable.alert_rounded_cardview)
            }
            1 -> {
                holder.cardView.setBackgroundResource(R.drawable.low_alert_rounded_cardview)
            }
            else -> {
                holder.cardView.setBackgroundResource(R.drawable.rounded_cardview)
            }
        }

        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale_up))

        // This is a click listener. When the user clicks on a specific expiration date, a new page opens to show the list of items that expire on the same date.
        holder.cardView.setOnClickListener {

            val intent = Intent(holder.itemView.context, ListOfItemsActivity::class.java)

            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(holder.itemView.context as Activity,  holder.cardView,  holder.cardView.transitionName )

            intent.putExtra("productsList", pendingExpireTotalItemsList[key] as Serializable)

            holder.itemView.context.startActivity(intent, option.toBundle())
        }

    }
}