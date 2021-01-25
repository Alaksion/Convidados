package com.example.convidados.service.repostiory

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.convidados.service.model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDatabase : RoomDatabase() {

    abstract fun guestDao(): GuestDAO

    companion object {
        private lateinit var INSTANCE: GuestDatabase
        fun getDatabase(context: Context): GuestDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context, GuestDatabase::class.java, "GuestDb")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}