package com.example.shoppinglist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(ShoppingList::class), version = 1)
abstract class ShoppingListRoomDatabase : RoomDatabase(){

    abstract fun shoppingListDao() : ShoppingListDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingListRoomDatabase? = null

        //Painfully obvious notes because code copied more or less from the google codelab on Room

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ShoppingListRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingListRoomDatabase::class.java,
                    "word_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(ShoppingListDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
        private class ShoppingListDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.shoppingListDao())
                    }
                }
            }
        }
        fun populateDatabase(shoppinListDao: ShoppingListDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.

        }
    }

}