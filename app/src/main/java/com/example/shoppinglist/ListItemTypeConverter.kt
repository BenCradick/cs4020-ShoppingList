package com.example.shoppinglist

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson

import java.util.*


//Stole wholesale from https://medium.com/@toddcookevt/android-room-storing-lists-of-objects-766cca57e3f9

class ListItemTypeConverter {

//JSON can be stored in a relational database because its a string

    companion object {

        @TypeConverter
        @JvmStatic
        fun stringToItemList(data: String?): List<ListItem> {
            val gson = Gson() // java object notation
            if (data == null) {
                return Collections.emptyList()
            }

            val listType = object : TypeToken<List<ListItem>>() {

            }.type //gets the type of the list

            return gson.fromJson(data, listType) //converts the underlying java object to JSON
        }

        @TypeConverter
        @JvmStatic
        fun itemListToString(someObjects: List<ListItem>): String { //same deal except the reverse
            val gson = Gson()
            return gson.toJson(someObjects)
        }
    }
}