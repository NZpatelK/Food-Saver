package com.example.foodsaver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ItemAdapter (private var itemList: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ItemName: TextView = view.findViewById(R.id.itemName)

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
        holder.ItemName.text = name.productName

        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale_up))

        holder.cardView.setOnClickListener {
            val jsonDataManager = JsonDataManager(holder.itemView.context)
            val formatter = DateTimeFormatter.ofPattern("d/MM/yyyy")

            val key = LocalDate.parse(name.expireDate, formatter) // key to update
            jsonDataManager.deleteTheItem(position, this, key)

            itemList = ItemDataHolder.groupOfSameExpireDate[key]!!

            for (i in position until itemList.size) {
                notifyItemChanged(i)
            }

        }
    }
}