package com.example.foodsaver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * This Adapter class controls the view of the list of items page.
 */
class ItemAdapter (private var itemList: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.itemName)
        val delete: ImageView = view.findViewById(R.id.delete)
        val cardView: CardView = view.findViewById(R.id.itemCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)

        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val name = itemList[position]
        holder.itemName.text = name.productName

        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale_up))

        //This is the delete button used to remove an item from the list.
        holder.delete.setOnClickListener {
            val jsonDataManager = JsonDataManager(holder.itemView.context)
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

            val key = LocalDate.parse(name.expireDate, formatter) // key to update
            jsonDataManager.deleteTheItem(position, this, key)// Delete the item from the data holder.

            itemList = ItemDataHolder.groupOfSameExpireDate[key]!!

            /**
             * When an item is deleted, the list of items
             * should be updated to ensure that the correct item is deleted and to prevent deleting the wrong one.
             */
            for (i in position until itemList.size) {
                notifyItemChanged(i)
            }

        }
    }
}