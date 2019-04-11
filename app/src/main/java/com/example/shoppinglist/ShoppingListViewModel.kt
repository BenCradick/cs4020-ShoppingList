package com.example.shoppinglist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ShoppingListViewModel(application : Application) : AndroidViewModel(application){
    private var repository : ShoppingListRepository
    private var allShoppingLists : LiveData<List<ShoppingList>>

    private var parentJob = Job()
    private val couroutineContext : CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(couroutineContext)
    init{
        val shoppingListDao = ShoppingListRoomDatabase.getDatabase(application, scope = CoroutineScope(couroutineContext)).shoppingListDao()
        repository = ShoppingListRepository(shoppingListDao)
        allShoppingLists = repository.allShoppingLists

    }





    fun insert(list : ShoppingList) = scope.launch(Dispatchers.IO){
        repository.insert(list)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}