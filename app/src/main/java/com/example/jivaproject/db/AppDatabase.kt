package com.example.jivaproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jivaproject.jiva.data.model.SellerTable
import com.example.jivaproject.jiva.data.model.VillageTable
import com.example.jivaproject.jiva.domain.dao.SellerDao

@Database(entities = [SellerTable::class,VillageTable::class], version = 1, exportSchema = false)
//@TypeConverters(Convertors::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSellerDao(): SellerDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(ctx: Context): AppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, AppDatabase::class.java,
                    "jiva_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

            return instance!!

        }
    }
}