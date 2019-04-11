package com.example.shoppinglist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoppinglist.ShoppingList

@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM shopping_lists ORDER BY list_name ASC")
    fun getAllLists() : LiveData<List<ShoppingList>>

    @Insert
    fun insertList(shoppingList : ShoppingList)

    @Update
    fun updateLists(vararg shoppingLists : List<ShoppingList>)

    @Query("DELETE FROM shopping_lists WHERE id = :id")
    fun deleteList(id: Int)

}