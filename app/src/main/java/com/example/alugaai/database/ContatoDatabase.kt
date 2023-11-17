package com.example.alugaai.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.alugaai.model.Contato

@Database(entities = [Contato::class], version = 1)
abstract class ContatoDatabase : RoomDatabase() {

    abstract fun contatoDao(): ContatoDao

    companion object {

        private lateinit var instance: ContatoDatabase

        fun getDatabase(context: Context): ContatoDatabase {
            if (!::instance.isInitialized) {
                instance =
                    Room.databaseBuilder(context, ContatoDatabase::class.java, "contato_database")
                        .allowMainThreadQueries().fallbackToDestructiveMigration().build()
            }
            return instance
        }
    }
}