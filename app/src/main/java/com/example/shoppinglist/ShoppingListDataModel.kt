package com.example.shoppinglist

import androidx.room.*

data class ListItem(
    var item : String,
    var quantity : Int,
    var checked : Boolean,
    var price : Float?,
    var id : Long
)



@Entity(indices = arrayOf(Index(value = ["list_name"], unique = true)), tableName = "shopping_lists")
data class ShoppingList(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "list_name") var listName : String,
    //I realize this is suboptimal if I need to query these items later and ForeignKey would be better
    @Embedded var items : List<ListItem>?
)