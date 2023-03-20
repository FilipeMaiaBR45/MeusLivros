package com.example.meuslivros.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.meuslivros.dao.LivroDao
import com.example.meuslivros.model.Livro
import java.security.AccessControlContext

@Database(entities = [Livro::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun livroDao() : LivroDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "livro_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}