package com.example.shoppinglist

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.shoppinglist.ShoppingList
import com.example.shoppinglist.ShoppingListDao

class ShoppingListRepository(private val shoppingListDao : ShoppingListDao){
    val allShoppingLists : LiveData<List<ShoppingList>> = shoppingListDao.getAllLists()

    @WorkerThread
    suspend fun insert(shoppingList: ShoppingList){
        shoppingListDao.insertList(shoppingList)
    }
}