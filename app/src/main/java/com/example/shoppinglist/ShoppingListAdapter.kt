package com.example.shoppinglist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShoppingListAdapter internal constructor(
    context : Context
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var shoppingLists = emptyList<ShoppingList>()

    inner class ShoppingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shoppingLostItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return ShoppingListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val current = shoppingLists[position]
        holder.shoppingLostItemView.text = current.listName
    }

    internal fun setShoppingLists(lists : List<ShoppingList>){
        this.shoppingLists = lists
        notifyDataSetChanged()
    }

    override fun getItemCount() = shoppingLists.size

}