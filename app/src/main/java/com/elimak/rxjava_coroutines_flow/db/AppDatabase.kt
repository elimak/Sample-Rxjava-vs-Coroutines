package com.elimak.rxjava_coroutines_flow.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elimak.rxjava_coroutines_flow.db.country.Country
import com.elimak.rxjava_coroutines_flow.db.country.CountryDaoFlow
import com.elimak.rxjava_coroutines_flow.db.country.CountryDaoRx

@Database(entities = [Country::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun countryDaoRx(): CountryDaoRx
    abstract fun countryDaoFlow(): CountryDaoFlow

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) { INSTANCE ?: buildDatabase(context).also { INSTANCE = it } }

        private fun buildDatabase(ctx: Context) =
            Room.databaseBuilder(ctx.applicationContext, AppDatabase::class.java, "app_database")
                .fallbackToDestructiveMigration()
                .build()
    }
}